package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.media.MediaGallery;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "authors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique",
                        columnNames = {"name"}
                )},
        indexes = {
                @Index(name = "idx_author_name", columnList = "name"),
                @Index(name = "idx_author_archived", columnList = "archived")
        }
)
public class Author extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "archived")
    @Builder.Default
    private Boolean archived = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_gallery_id")
    @JsonIgnore
    private MediaGallery image;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private Set<BookAuthor> books;
}
