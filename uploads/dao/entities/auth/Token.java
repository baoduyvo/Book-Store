package org.voduybao.tools.dao.entities.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.voduybao.tools.dao.entities.user.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tokens")
public class Token {

    @Id
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "access_token", columnDefinition = "TEXT", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", columnDefinition = "TEXT", nullable = false)
    private String refreshToken;

    @Size(max = 50)
    @Column(name = "jti", unique = true, nullable = false)
    private String jti;

    @Column(name = "expired_at", nullable = false)
    private Date expiredAt;

    @Column(name = "revoked", nullable = false)
    @Builder.Default
    private boolean revoked = false;

    @PrePersist
    public void handleBeforeCreate() {
        if (this.expiredAt == null) {
            this.expiredAt = Date.from(Instant.now().plus(60, ChronoUnit.MINUTES));
        }
    }

}
