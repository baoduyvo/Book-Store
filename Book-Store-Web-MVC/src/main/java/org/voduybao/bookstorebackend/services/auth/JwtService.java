package org.voduybao.bookstorebackend.services.auth;

import org.voduybao.bookstorebackend.dao.entities.user.User;

import java.time.Instant;

public interface JwtService {
    String generateAccessToken(User user, String jti, Instant issuedAt, Instant expiry);

    String generateRefreshToken(User user, String jti, Instant issuedAt, Instant expiry);

    boolean extractTokenExpired(String token);

    String extractAccessTokenEmail(String token);

    String extracJti(String token);

    boolean verificationToken(String token, User user);
}
