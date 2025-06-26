package org.voduybao.tools.dao.entities.feedback;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "suggested_contents")
public class Suggested extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Integer id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "link", length = 255)
    private String link;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}
