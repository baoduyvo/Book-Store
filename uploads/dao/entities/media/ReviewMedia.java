package org.voduybao.tools.dao.entities.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.contants.e.FileTypeEnum;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.feedback.Review;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review_media")
public class ReviewMedia extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_media_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", length = 10)
    private FileTypeEnum type;

    @Column(name = "sequence")
    private Integer sequence = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    @JsonIgnore
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", nullable = false)
    @JsonIgnore
    private MediaGallery media;
}
