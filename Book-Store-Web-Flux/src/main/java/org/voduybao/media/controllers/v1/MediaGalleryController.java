package org.voduybao.media.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.voduybao.media.services.FileStorageService;
import org.voduybao.tools.dao.entities.media.MediaGallery;
import org.voduybao.tools.exception.error.ResponseErrors;
import org.voduybao.tools.response.http.Result;
import org.voduybao.tools.utils.FileUtil;
import org.voduybao.tools.utils.Snowflake;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class MediaGalleryController {

    @Setter(onMethod_ = @Autowired)
    private FileStorageService fileStorageService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Result> uploadFile(
            @RequestPart("file") Mono<FilePart> filePartMono
    ) {
        return filePartMono.flatMap(filePart -> saveFile(filePart))
                .map(Result::content)
                .onErrorResume(throwable -> {
                    log.error("Upload file failed: {}", throwable.getMessage(), throwable);
                    return Mono.just(Result.failure(ResponseErrors.UNSUPPORTED_MEDIA_TYPE));
                });
    }

    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Result> uploadFiles(@RequestPart("files") Flux<FilePart> fileParts) {
        return fileParts.flatMap(filePart -> saveFile(filePart))
                .collectList()
                .map(Result::content)
                .onErrorResume(throwable -> {
                    log.error("Uploads file failed: {}", throwable.getMessage(), throwable);
                    return Mono.just(Result.failure(ResponseErrors.UNSUPPORTED_MEDIA_TYPE));
                });
    }


    private Mono<MediaGallery> saveFile(FilePart fp) {
        String fileOriginalName = fp.filename();
        String extension = FileUtil.getExtensionName(fileOriginalName);

        String mimeType = Optional.ofNullable(fp.headers().getContentType())
                .map(MediaType::toString)
                .orElse("application/octet-stream");

        String objectName = String.valueOf(Snowflake.generateId());
        String fileName = FileUtil.createFileName(objectName, extension);

        Path tempDir = Paths.get("D:/book/uploads");
        try {
            if (!Files.exists(tempDir)) {
                Files.createDirectories(tempDir);
            }
        } catch (IOException e) {
            log.error("Error occurred while creating directories", e);
            return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create upload directory", e));
        }
        Path tempFile = tempDir.resolve(fileName);

        return fp.transferTo(tempFile)
                .then(Mono.fromCallable(() ->
                        fileStorageService.saveFile(
                                fileOriginalName,
                                mimeType,
                                fileName,
                                objectName,
                                fp
                        )
                ))
                .onErrorResume(e -> {
                    log.error("Error occurred while uploading file", e);
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed", e));
                })
                .doFinally(result -> {
                    try {
                        Files.deleteIfExists(tempFile);
                    } catch (IOException e) {
                        log.error("Error occurred while deleting temp file", e);
                    }
                });
    }
}

