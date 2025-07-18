package org.voduybao.media.services.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.voduybao.media.dao.repositories.MediaGalleryRepository;
import org.voduybao.media.services.FileStorageService;
import org.voduybao.media.services.MediaGalleryService;
import org.voduybao.media.services.MinioService;
import org.voduybao.tools.dao.entities.media.MediaGallery;

import java.util.Set;

@Component
@Slf4j(topic = "FILE-STORAGE-SERVICE")
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private MediaGalleryRepository mediaGalleryRepository;

    @Autowired
    private MediaGalleryService mediaGalleryService;
    @Autowired
    private MinioService minioService;

    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("image/jpeg", "image/png", "image/gif", "image/svg+xml");
    private static final Set<String> ALLOWED_VIDEO_TYPES = Set.of("video/mp4", "video/mpeg");
    private static final Set<String> ALLOWED_AUDIO_TYPES = Set.of("audio/mpeg", "audio/wav", "audio/ogg");


    @Override
    public MediaGallery saveFile(
            String fileOriginalName,
            String mimeType,
            String fileName,
            String objectName,
            FilePart fp
    ) throws Exception {
//        if (isAllowedMimeType(mimeType))
//            throw new ResponseException(ResponseErrors.UNSUPPORTED_MEDIA_TYPE);

        System.out.println("fileOriginalName: " + fileOriginalName);
        System.out.println("mimeType: " + mimeType);
        System.out.println("fileName: " + fileName);
        System.out.println("objectName: " + objectName);

//        String minioReturn = minioService.uploadFile(fileName, fp);

//        Mono<MediaGallery> mediaGalleryMono = mediaGalleryRepository.insertMediaGallery(
//                "fileName",
//                "fileOriginalName",
//                "extension",
//                "fileType",
//                "type",
//                true,
//                false
//        );
//        System.out.println("mediaGalleryMono: " + mediaGalleryMono.toString());

        mediaGalleryRepository.selectMediaGallery()
                .subscribe(media -> System.out.println("Fetched: " + media.toString()));

        return MediaGallery.builder()
                .fileName(fileName)
                .build();
    }

    private boolean isAllowedMimeType(String mimeType) {
        return ALLOWED_IMAGE_TYPES.contains(mimeType)
                || ALLOWED_VIDEO_TYPES.contains(mimeType)
                || ALLOWED_AUDIO_TYPES.contains(mimeType);
    }
}
