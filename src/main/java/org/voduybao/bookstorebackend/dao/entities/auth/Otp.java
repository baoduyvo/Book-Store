package org.voduybao.bookstorebackend.dao.entities.auth;

import jakarta.persistence.*;
import lombok.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "otp")
public class Otp {

    @Id
    @Column(name = "otp_id", updatable = false, nullable = false)
    private String otpId;

    @Column(name = "otp", length = 6, updatable = false, nullable = false)
    private String otp;

    @Column(name = "expiration", updatable = false, nullable = false)
    private Instant expiration;

    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @PrePersist
    public void handleBeforeCreate() {
        Instant now = Instant.now();
        this.createdAt = now;

        if (this.otpId == null &&
                this.otp == null &&
                this.expiration == null) {
            this.otpId = UUID.randomUUID().toString();
            this.otp = String.format("%06d", new SecureRandom().nextInt(1_000_000));
            this.expiration = this.createdAt.plus(5, ChronoUnit.MINUTES);
        }
    }

}
