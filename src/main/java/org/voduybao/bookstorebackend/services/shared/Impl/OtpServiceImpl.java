package org.voduybao.bookstorebackend.services.shared.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.auth.Otp;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.dao.repositories.auth.OtpRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.UserRepository;
import org.voduybao.bookstorebackend.dtos.OtpDto;
import org.voduybao.bookstorebackend.services.notification.EmailService;
import org.voduybao.bookstorebackend.services.shared.OtpService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.time.Instant;

@Component
@Slf4j(topic = "OTP")
public class OtpServiceImpl implements OtpService {
    @Setter(onMethod_ = @Autowired)
    private OtpRepository otpRepository;
    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Setter(onMethod_ = @Autowired)
    private EmailService emailService;

    @Override
    public void sendOtp(OtpDto.Request request) {
        log.info("Sending OTP...");
        User user = request.isEmail()
                ? userRepository.findUserByEmail(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED))
                : userRepository.findUserByPhone(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));
        Otp otp = otpRepository.save(Otp.builder().build());

        if (request.isEmail()) {
            emailService.sendVerifyOtp(user.getEmail(), otp.getOtp());
        } else if (request.isPhone()) {
            // TODO: Tích hợp dịch vụ SMS (nếu có)
        }
    }

    @Override
    public void verifyOtp(OtpDto.Request request) {
        User user = request.isEmail()
                ? userRepository.findUserByEmail(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED))
                : userRepository.findUserByPhone(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));

        Otp otp = otpRepository.findOtp(request.getOtp())
                .orElseThrow(() -> new ResponseException(ResponseErrors.VERIFICATION_CODE_NOT_FOUND));

        if (otp.getExpiration().isBefore(Instant.now())) {
            throw new ResponseException(ResponseErrors.VERIFICATION_CODE_EXPIRED);
        }

        if (!otp.getOtp().equals(request.getOtp())) {
            throw new ResponseException(ResponseErrors.VERIFICATION_CODE_NOT_FOUND);
        }

        user.setIsActive(true);
        user.setIsVerified(true);
        userRepository.save(user);
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void cleanExpiredOtps() {
        int deletedCount = otpRepository.deleteByExpirationBefore(Instant.now());
        if (deletedCount > 0) {
            log.info("Deleted {} expired OTP(s)", deletedCount);
        }
    }

}