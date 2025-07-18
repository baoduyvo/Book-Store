package org.voduybao.tools.dao.entities.media;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaGallery {

    private Integer mediaGalleryId;
    private String fileName;
    private String fileOriginalName;
    private String extension;
    private String fileURL;
    private String fileType;
    private String type;
    private String mimeType;
    private Boolean isActive;
    private Boolean isDeleted;
    private Instant created_at;
    private Instant updated_at;

}
