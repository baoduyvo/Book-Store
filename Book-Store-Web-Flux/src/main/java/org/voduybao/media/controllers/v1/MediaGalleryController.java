package org.voduybao.media.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class MediaGalleryController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Result> uploadFile(@RequestPart("file") Mono<FilePart> filePartMono) {
        return filePartMono.flatMap(filePart -> {
            String filename = UUID.randomUUID() + "_" + filePart.filename();
            Path path = Paths.get("uploads/" + filename);
            return filePart.transferTo(path)
                    .thenReturn(Result.content(Map.of(
                            "fileName", filename,
                            "originalName", filePart.filename()
                    )));
        });
    }

    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFiles(@RequestPart("files") Flux<FilePart> fileParts) {
        return fileParts.flatMap(filePart -> filePart.transferTo(new File(filePart.filename())))
                .then(Mono.just("Files uploaded successfully"));
    }
}
