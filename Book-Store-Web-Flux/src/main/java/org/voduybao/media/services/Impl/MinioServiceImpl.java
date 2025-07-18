package org.voduybao.media.services.Impl;

import io.minio.*;
import io.minio.errors.ErrorResponseException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.voduybao.media.services.MinioService;

import java.io.InputStream;
import java.util.Objects;

@Component
@Slf4j(topic = "MINIO")
public class MinioServiceImpl implements MinioService {

    @Setter(onMethod_ = @Autowired)
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public String uploadFile(String fileName, FilePart fp) throws Exception {
        DataBuffer dataBuffer = DataBufferUtils.join(fp.content()).block();

        if (dataBuffer == null) {
            throw new RuntimeException("Failed to read file content");
        }

        InputStream inputStream = dataBuffer.asInputStream();
        long size = dataBuffer.readableByteCount();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, size, -1)
                        .contentType(Objects.requireNonNull(fp.headers().getContentType()).toString())
                        .build()
        );
        DataBufferUtils.release(dataBuffer);
        return fileName;
    }

    @Override
    public byte[] downloadFile(String fileName) throws Exception {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build())) {
            return stream.readAllBytes();
        }
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    @Override
    public boolean fileExists(String fileName) throws Exception {
        try {
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
            return true;
        } catch (ErrorResponseException e) {
            if (e.errorResponse().code().equals("NoSuchKey")) {
                return false;
            }
            throw e;
        }
    }

    @Override
    public void autoCreateBucket() throws Exception {
        if (!minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
        )) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            log.info("✅ MinIO bucket '{}' created successfully.", bucketName);
        }
    }

}

