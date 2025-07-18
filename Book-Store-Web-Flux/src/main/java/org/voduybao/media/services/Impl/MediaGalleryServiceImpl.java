package org.voduybao.media.services.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.voduybao.media.services.MediaGalleryService;
import org.voduybao.media.services.MinioService;
import org.voduybao.tools.response.http.Result;

@Component
@Slf4j(topic = "MEDIA-GALLERY-SERVICE")
public class MediaGalleryServiceImpl implements MediaGalleryService {

    @Autowired
    private MinioService minioService;

    @Override
    public Result upload(FilePart file, String type) {
        log.info("Uploading file");


        return null;
    }

    @Override
    public String createBucket(String bucketName) {
        log.info("Create Bucket");



        return "";
    }
}
