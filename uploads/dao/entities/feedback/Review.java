package org.voduybao.tools.dao.entities.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.contants.e.StatusEnum;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.media.ReviewMedia;
import org.voduybao.tools.dao.entities.merchandise.Book;


import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reviews")
public class Review extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "analysis", columnDefinition = "TEXT")
    private String analysis;

    @Column(name = "suggested", columnDefinition = "TEXT")
    private String suggested;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private StatusEnum status;

    @OneToMany(mappedBy = "review")
    private Set<ReviewMedia> reviewMedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    @JsonIgnore
    private ReviewerArea reviewerArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnore
    private Area area;

    @OneToMany(mappedBy = "review")
    private Set<ReviewPackage> reviewPackages;
}
