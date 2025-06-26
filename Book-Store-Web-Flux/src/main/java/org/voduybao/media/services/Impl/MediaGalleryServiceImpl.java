package org.voduybao.media.services.Impl;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.voduybao.media.services.MediaGalleryService;
import org.voduybao.tools.dao.repository.media.MediaGalleryRepository;
import org.voduybao.tools.response.http.Result;
import reactor.core.publisher.Mono;

@Component
@Slf4j(topic = "MEDIA-GALLERY-SERVICE")
public class MediaGalleryServiceImpl implements MediaGalleryService {

    @Setter(onMethod_ = @Autowired)
    private MediaGalleryRepository mediaGalleryRepository;

    @Override
    public Result upload(Mono<FilePart> file, String type) {
        log.info("Uploading file");


        return null;
    }
}
