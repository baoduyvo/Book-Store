package org.voduybao.tools.dao.repository.auth;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.tools.dao.entities.auth.Otp;

import java.time.Instant;
import java.util.Optional;

public interface OtpRepository extends CrudRepository<Otp, String> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Otp o WHERE o.expiration < :time")
    int deleteByExpirationBefore(Instant time);

    @Query("SELECT o FROM Otp o WHERE o.otp = :otp")
    Optional<Otp> findOtp(@Param("otp") String otp);
}