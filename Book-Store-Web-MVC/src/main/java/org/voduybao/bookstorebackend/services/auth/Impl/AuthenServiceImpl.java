package org.voduybao.bookstorebackend.services.auth.Impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.auth.Role;
import org.voduybao.bookstorebackend.dao.entities.auth.Token;
import org.voduybao.bookstorebackend.dao.entities.user.User;
import org.voduybao.bookstorebackend.dao.repositories.auth.RoleRepository;
import org.voduybao.bookstorebackend.dao.repositories.auth.TokenRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.UserRepository;
import org.voduybao.bookstorebackend.dtos.AuthenDto;
import org.voduybao.bookstorebackend.services.auth.AuthenService;
import org.voduybao.bookstorebackend.services.auth.JwtService;
import org.voduybao.bookstorebackend.services.auth.SocialAuthService;
import org.voduybao.bookstorebackend.services.auth.TokenService;
import org.voduybao.bookstorebackend.tools.contants.e.AuthProviderEnum;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.security.password.PasswordUtils;
import org.voduybao.bookstorebackend.tools.utils.StrUtil;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenServiceImpl implements AuthenService {
    @Setter(onMethod_ = @Autowired)
    private PasswordUtils passwordUtils;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;
    @Setter(onMethod_ = @Autowired)
    private TokenRepository tokenRepository;
    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;

    @Setter(onMethod_ = @Autowired)
    private TokenService tokenService;
    @Setter(onMethod_ = @Autowired)
    private JwtService jwtService;
    @Setter(onMethod_ = @Autowired)
    private SocialAuthService socialAuthService;

    @Override
    public void reigster(AuthenDto.RegisterRequest request) {
        log.info("Authentication registered ...!");
        if (userRepository.existsUserByEmail(request.getEmail()))
            throw new ResponseException(ResponseErrors.EMAIL_VERIFIED);

        if (userRepository.existsUserByPhoneNumber(request.getPhone()))
            throw new ResponseException(ResponseErrors.PHONE_VERIFIED);

        User user = User.builder()
                .phoneNumber(request.getPhone())
                .email(request.getEmail())
                .password(passwordUtils.hashPassword(request.getPassword()))
                .authProvider(AuthProviderEnum.LOCAL)
                .roles(Set.of(roleRepository.getCustomerRole()))
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthenDto.LoginResponse login(
            AuthenDto.LoginRequest request,
            HttpServletResponse response) {
        log.info("Authentication sign in ...!");

        User user = request.isEmail()
                ? userRepository.findUserByEmail(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED))
                : userRepository.findUserByPhone(request.getPhoneOrEmail())
                .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));

        if (Boolean.FALSE.equals(user.getIsVerified()))
            throw new ResponseException(ResponseErrors.ACCOUNT_EXISTS_WAIT_FOR_VERIFYING);

        if (Boolean.FALSE.equals(user.getIsActive()))
            throw new ResponseException(ResponseErrors.USER_IS_DEACTIVATED);

        if (!passwordUtils.checkPassword(request.getPassword(), user.getPassword()))
            throw new ResponseException(ResponseErrors.PASSWORD_INCORRECT);

        Token tokenData = tokenService.createAndSaveToken(user, response);

        return AuthenDto.LoginResponse.builder()
                .userId(user.getUserId())
                .accessToken(tokenData.getAccessToken())
                .refreshToken(tokenData.getRefreshToken())
                .build();
    }

    @Override
    public void logout(AuthenDto.TokenRequest request, HttpServletResponse response) {
        log.info("Authentication sign out ...!");
        String email = jwtService.extractAccessTokenEmail(request.getAccessToken());
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

        jwtService.verificationToken(request.getAccessToken(), user);

        String jti = jwtService.extracJti(request.getAccessToken());
        Token token = tokenRepository.findByJti(jti)
                .orElseThrow(() -> new ResponseException(ResponseErrors.TOKEN_INVALID));

        boolean checkAccessTokenExpTime = jwtService.extractTokenExpired(request.getAccessToken());
        if (!checkAccessTokenExpTime) {
            tokenService.revokeToken(token, response);
        }

    }

    @Override
    public AuthenDto.LoginResponse refresh(String refreshToken, HttpServletResponse response) {
        log.info("Authentication refresh token ...!");
        if (StrUtil.isBlank(refreshToken))
            throw new ResponseException(ResponseErrors.REFRESH_TOKEN_INVALID);

        String email = jwtService.extractAccessTokenEmail(refreshToken);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

        boolean isVerifi = jwtService.verificationToken(refreshToken, user);
        if (!isVerifi)
            throw new ResponseException(ResponseErrors.REFRESH_TOKEN_INVALID);

        Token token = tokenService.createAndSaveToken(user, response);

        return AuthenDto.LoginResponse.builder()
                .userId(user.getUserId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public AuthenDto.UserResponse introspect(AuthenDto.TokenRequest request) {
        log.info("Authentication introspect token ...!");
        String email = jwtService.extractAccessTokenEmail(request.getAccessToken());
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

        jwtService.verificationToken(request.getAccessToken(), user);

        return AuthenDto.UserResponse.builder()
                .userId(user.getUserId())
                .phone(user.getPhoneNumber())
                .email(user.getEmail())
                .role(
                        user.getRoles().stream()
                                .map(Role::getRoleName)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public String socailAuthType(String loginType) {
        log.info("Authentication login return base url...!");
        return socialAuthService.baseUrl(loginType);
    }

    @Override
    public AuthenDto.LoginResponse callback(String code, String loginType, HttpServletResponse response) {
        log.info("Authentication login callback...!");
        User user = socialAuthService.handleCallback(code, loginType);

        Token token = tokenService.createAndSaveToken(user, response);

        return AuthenDto.LoginResponse.builder()
                .userId(user.getUserId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}