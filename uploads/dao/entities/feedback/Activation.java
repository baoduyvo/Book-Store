package org.voduybao.tools.dao.entities.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.voduybao.tools.dao.entities.common.metadata.TimeStamped;
import org.voduybao.tools.dao.entities.user.User;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activation_codes")
public class Activation extends TimeStamped {

    @Id
    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "is_used")
    private Boolean isUsed = false;

    @Column(name = "used_at")
    private Instant usedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnore
    private Area area;
}
