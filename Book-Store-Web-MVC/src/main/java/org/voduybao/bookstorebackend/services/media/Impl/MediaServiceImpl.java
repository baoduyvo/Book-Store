package org.voduybao.bookstorebackend.services.media.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.repositories.media.MediaGalleryRepository;
import org.voduybao.bookstorebackend.services.media.MediaService;

@Component
@Slf4j(topic = "MEDIA-SERVICE")
public class MediaServiceImpl implements MediaService {

    @Setter(onMethod_ = @Autowired)
    private MediaGalleryRepository MediaGalleryRepository;

//    fileOriginalName,fileURL,fileName,extension,fileType,type,
}
