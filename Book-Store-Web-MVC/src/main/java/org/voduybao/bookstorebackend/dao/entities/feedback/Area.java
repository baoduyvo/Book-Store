package org.voduybao.bookstorebackend.dao.entities.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.merchandise.BookAuthor;
import org.voduybao.bookstorebackend.tools.contants.e.ReviewEnum;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review_areas")
public class Area extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_area_type", length = 10)
    private ReviewEnum typeReview;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private Set<ReviewerArea> reviewerAreas;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private Set<BookArea> bookArea;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private Set<Package> packages;

    @OneToMany(mappedBy = "area")
    @JsonIgnore
    private Set<Activation> activations;
}
