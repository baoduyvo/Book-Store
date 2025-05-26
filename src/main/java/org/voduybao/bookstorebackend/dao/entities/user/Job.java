package org.voduybao.bookstorebackend.dao.entities.user;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.bookstorebackend.dao.entities.common.metadata.TimeStamped;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jobs")
public class Job extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

}
