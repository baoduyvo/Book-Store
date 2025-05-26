package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.embedded.Address;
import org.voduybao.bookstorebackend.dao.entities.embedded.MetaDataTimeStampedEntity;
import org.voduybao.bookstorebackend.dao.entities.embedded.SizeAndWeight;
import org.voduybao.bookstorebackend.dao.entities.media.MediaGallery;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "contact", columnDefinition = "TEXT")
    private String contact;

    @Embedded
    private MetaDataTimeStampedEntity timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_gallery_id", nullable = false)
    @JsonIgnore
    private MediaGallery image;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
