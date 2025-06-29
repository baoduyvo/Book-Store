package org.voduybao.bookstorebackend.dao.entities.merchandise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.common.embedded.DimensionAndWeight;
import org.voduybao.bookstorebackend.dao.entities.feedback.BookArea;
import org.voduybao.bookstorebackend.dao.entities.media.MediaGallery;
import org.voduybao.bookstorebackend.dao.entities.user.ReadingHistory;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "language", length = 50)
    private String language;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "format", length = 50)
    private String format;

    @Column(name = "archived")
    private Boolean archived = false;

    @Embedded
    private DimensionAndWeight sizeAndWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_gallery_id", nullable = false)
    @JsonIgnore
    private MediaGallery image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    @JsonIgnore
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<BookAuthor> authors;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Quote> quotes;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<ReadingHistory> readingHistories;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private BookArea bookArea;
}
