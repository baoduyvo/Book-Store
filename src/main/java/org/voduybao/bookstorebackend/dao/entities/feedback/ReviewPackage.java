package org.voduybao.bookstorebackend.dao.entities.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;
import org.voduybao.bookstorebackend.dao.entities.merchandise.HashTag;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Product;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review_package_items")
public class ReviewPackage extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_item_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    @JsonIgnore
    private Package aPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    @JsonIgnore
    private Review review;
}
