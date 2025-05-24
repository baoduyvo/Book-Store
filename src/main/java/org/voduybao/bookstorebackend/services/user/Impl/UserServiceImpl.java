package org.voduybao.bookstorebackend.services.user.Impl;

import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.dao.repositories.user.UserRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.join.UserUserProfileJoin;
import org.voduybao.bookstorebackend.dtos.UserDto;
import org.voduybao.bookstorebackend.services.notification.EmailService;
import org.voduybao.bookstorebackend.services.notification.SMSService;
import org.voduybao.bookstorebackend.services.user.UserService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;
import org.voduybao.bookstorebackend.tools.security.password.PasswordUtils;
import org.voduybao.bookstorebackend.tools.utils.PaginationUtils;
import org.voduybao.bookstorebackend.tools.utils.Utils;

import java.util.List;

@Component
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {
    @Setter(onMethod_ = @Autowired)
    private PasswordUtils passwordUtils;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Setter(onMethod_ = @Autowired)
    private EmailService emailService;
    @Setter(onMethod_ = @Autowired)
    private SMSService smsService;

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
        String otp = Utils.generateOtp();
        if (request.isEmail()) {
            User user = userRepository.findUserByEmail(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED));
            emailService.sendVerifyOtp(user.getEmail(), otp);
        } else if (request.isPhone()) {
            User user = userRepository.findUserByPhone(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));
            smsService.sendVerifyOtp(user.getPhoneNumber(), otp);
        }
    }

    @Override
    public void confirmPassword(int userID, UserDto.ConfirmForgotPasswordRequest request) {
        log.info("User Confirm Forgot Password ...!");
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.USER_NOT_FOUND));
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