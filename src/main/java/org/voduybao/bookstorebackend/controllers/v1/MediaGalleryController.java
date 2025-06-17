package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/files")
@Tag(name = "99 - File And Media Gallery Controller", description = "API quản lý các file về media gallery")
@RequiredArgsConstructor
//@AdminRequired
public class MediaGalleryController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") Mono<FilePart> file) {
        log.info("Received upload request");
        return file
                .doOnNext(f -> {
                    log.info("File name: {}", f.filename());
                    log.info("Headers: {}", f.headers());
                })
                .flatMap(f -> Mono.just("Uploaded: " + f.filename()));
    }


}