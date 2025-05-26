package org.voduybao.bookstorebackend.dao.entities.user;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.embedded.MetaDataTimeStampedEntity;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edu_id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    @Embedded
    private MetaDataTimeStampedEntity timeStamp;
}
