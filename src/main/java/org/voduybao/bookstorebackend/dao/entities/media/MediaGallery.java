package org.voduybao.bookstorebackend.dao.entities.media;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.embedded.FileEntry;
import org.voduybao.bookstorebackend.dao.entities.embedded.MetaDataTimeStampedEntity;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Book;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Publisher;
import org.voduybao.bookstorebackend.tools.contants.e.FileTypeEnum;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "media_gallery")
public class MediaGallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_gallery_id")
    private Integer mediaGalleryId;

    @Column(name = "file_name", length = 255)
    private String fileName;

    @Column(name = "file_original_name", length = 255)
    private String fileOriginalName;

    @Column(name = "extension", length = 20)
    private String extension;

    @Column(name = "file_url", columnDefinition = "TEXT")
    private String fileURL;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private FileTypeEnum fileType;

    @Column(name = "type", length = 68)
    private String type;

    @Embedded
    private FileEntry fileEntry;

    @Column(name = "mime_type", length = 68)
    private String mimeType;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "media")
    private Set<UserMedia> usedByUsers;

    @OneToMany(mappedBy = "image")
    private Set<Book> books;

    @OneToMany(mappedBy = "image")
    private Set<Publisher> publishers;

    @Embedded
    private MetaDataTimeStampedEntity timeStamp;

    @PrePersist
    public void handleBeforeCreate() {
        if(this.isActive == null  || this.isDeleted == null) {
            this.isActive = true;
            this.isDeleted = false;
        }
    }

}
