package org.voduybao.bookstorebackend.dao.entities.auth;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "otp_id", updatable = false)
    private String otpId;

    @Column(name = "otp", length = 50, updatable = false)
    private String otp;

    @Column(name = "expiration", updatable = false)
    private Instant expiration;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
    }

}
