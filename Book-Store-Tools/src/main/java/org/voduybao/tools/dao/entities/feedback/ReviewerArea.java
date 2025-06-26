package org.voduybao.tools.dao.entities.feedback;

import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.dao.entities.user.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reviewer_areas")
public class ReviewerArea {

    @Id
    private Integer userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "reviewer_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "fk_reviewer_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;;
}