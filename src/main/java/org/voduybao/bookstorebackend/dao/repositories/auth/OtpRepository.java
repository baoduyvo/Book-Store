package org.voduybao.bookstorebackend.dao.repositories.auth;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.auth.Otp;

import java.time.Instant;
import java.util.Optional;

public interface OtpRepository extends CrudRepository<Otp, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Otp o WHERE o.expiration < :time")
    int deleteByExpirationBefore(Instant time);

    @Modifying
    @Transactional
    @Query("SELECT o FROM Otp o WHERE o.otp = :otp")
    Optional<Otp> findByIdOtp(@Param("otp") String otp);
}