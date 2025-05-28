package org.voduybao.bookstorebackend.services.user.Impl;

import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.auth.Otp;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.dao.repositories.auth.OtpRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.UserRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.join.UserUserProfileJoin;
import org.voduybao.bookstorebackend.dtos.OtpDto;
import org.voduybao.bookstorebackend.dtos.UserDto;
import org.voduybao.bookstorebackend.services.shared.OtpService;
import org.voduybao.bookstorebackend.services.user.UserService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;
import org.voduybao.bookstorebackend.tools.security.password.PasswordUtils;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationUtils;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {
    @Setter(onMethod_ = @Autowired)
    private PasswordUtils passwordUtils;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;
    @Setter(onMethod_ = @Autowired)
    private OtpRepository otpRepository;

    @Setter(onMethod_ = @Autowired)
    private OtpService otpService;

    @Override
    @Transactional
    public void changePassword(int userID, UserDto.ChangePasswordRequest request) {
        log.info("User Change Password ...!");
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.USER_NOT_FOUND));

        user.setPassword(passwordUtils.hashPassword(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void forgotPassword(UserDto.ForgotPasswordRequest request) {
        log.info("User Forgot Password ...!");
        otpService.sendOtp(new OtpDto.Request(request.getPhoneOrEmail(), null));
    }

    @Override
    public void confirmPassword(UserDto.ConfirmForgotPasswordRequest request) {
        log.info("User Confirm Forgot Password ...!");
        User user = null;
        if (request.isEmail()) {
            user = userRepository.findUserByEmail(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED));
        } else if (request.isPhone()) {
            user = userRepository.findUserByPhone(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));
        }

        Otp otp = otpRepository.findOtp(request.getOtp())
                .orElseThrow(() -> new ResponseException(ResponseErrors.VERIFICATION_CODE_NOT_FOUND));
        if (otp.getExpiration().isBefore(Instant.now())) {
            throw new ResponseException(ResponseErrors.VERIFICATION_CODE_EXPIRED);
        }
        if (!otp.getOtp().equals(request.getOtp())) {
            throw new ResponseException(ResponseErrors.VERIFICATION_CODE_NOT_FOUND);
        }

        user.setPassword(passwordUtils.hashPassword(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteAccount(int userID) {
        log.info("User Delete Account Get ID ...!");
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.USER_NOT_FOUND));
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    public PaginationResult<UserDto.UsersResponse> findAllUser(int page, int size) {
        log.info("User Get List Users With Paginations ...!");
        PaginationUtils.PaginationResult pagination = PaginationUtils.validateAndConvert(page, size);

        List<UserUserProfileJoin> results = userRepository.findAllUsers(pagination.pageSize(), pagination.offset());

        int totalPages = 0;
        long totalItems = userRepository.countItems();
        totalPages = PaginationUtils.calculateTotalPages(totalItems, pagination.pageSize());

        return new PaginationResult<>(totalPages,
                results.stream().map(UserDto.UsersResponse::fromEntity).toList());
    }
}