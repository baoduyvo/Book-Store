package org.voduybao.media.dao.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.voduybao.tools.dao.entities.media.MediaGallery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MediaGalleryRepository {

    private final DatabaseClient databaseClient;

    public Mono<MediaGallery> insertMediaGallery(
            String fileName,
            String fileOriginalName,
            String extension,
            String fileType,
            String type,
            Boolean isActive,
            Boolean isDeleted
    ) {
        return databaseClient.sql("""
                INSERT INTO media_gallery (
                    file_name, file_original_name, extension, file_type, type, is_active, is_deleted
                ) VALUES (:fileName, :fileOriginalName, :extension, :fileType, :type, :isActive, :isDeleted)
                """)
                .bind("fileName", fileName)
                .bind("fileOriginalName", fileOriginalName)
                .bind("extension", extension)
                .bind("fileType", fileType)
                .bind("type", type)
                .bind("isActive", isActive)
                .bind("isDeleted", isDeleted)
                .fetch()
                .rowsUpdated()
                .map(rows -> {
                    log.info("Inserted {} row(s)", rows);
                    return MediaGallery.builder()
                            .fileName(fileName)
                            .fileOriginalName(fileOriginalName)
                            .extension(extension)
                            .fileType(fileType)
                            .type(type)
                            .isActive(isActive)
                            .isDeleted(isDeleted)
                            .build();
                });
    }

    public Flux<MediaGallery> selectMediaGallery() {
        return databaseClient.sql("""
                SELECT 
                     file_original_name            
                FROM media_gallery
                """)
                .map((row, meta) -> MediaGallery.builder()
                        .fileName(row.get("file_original_name", String.class))
                        .build())
                .all()
                .doOnNext(media -> log.info("MediaGallery fetched: {}", media))
                .doOnComplete(() -> log.info("Finished fetching all MediaGallery records"))
                .onErrorResume(e -> {
                    log.error("Error while fetching media gallery", e);
                    return Flux.empty();
                });
    }
}
