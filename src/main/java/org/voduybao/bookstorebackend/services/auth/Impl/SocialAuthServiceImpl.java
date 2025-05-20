package org.voduybao.bookstorebackend.services.auth.Impl;

import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.entities.UserProfile;
import org.voduybao.bookstorebackend.dao.repositories.RoleRepository;
import org.voduybao.bookstorebackend.dao.repositories.UserProfileRepository;
import org.voduybao.bookstorebackend.dao.repositories.UserRepository;
import org.voduybao.bookstorebackend.services.auth.SocialAuthService;
import org.voduybao.bookstorebackend.services.auth.strategies.AuthStrategy;
import org.voduybao.bookstorebackend.services.auth.strategies.FacebookAuthStrategy;
import org.voduybao.bookstorebackend.services.auth.strategies.GoogleAuthStrategy;
import org.voduybao.bookstorebackend.tools.contants.AuthProviderEnum;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j(topic = "SOCIAL-SERVICE")
public class SocialAuthServiceImpl implements SocialAuthService {
    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;
    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;
    @Setter(onMethod_ = @Autowired)
    private UserProfileRepository userProfileRepository;

    @Setter(onMethod_ = @Autowired)
    private GoogleAuthStrategy googleAuthStrategy;
    @Setter(onMethod_ = @Autowired)
    private FacebookAuthStrategy facebookAuthStrategy;

    @Override
    public String baseUrl(String loginType) {
        log.info("Genaration base url connect socail...!");
        return switch (loginType.toUpperCase()) {
            case "GOOGLE" -> googleAuthStrategy.baseUrlGoogle();
            case "FACEBOOK" -> facebookAuthStrategy.baseUrlFacebook();
            default -> throw new ResponseException(ResponseErrors.NOT_SUPPORT_AUTH_TYPE);
        };
    }

    @Override
    @Transactional
    public User handleCallback(String code, String loginType) {
        log.info("Handle call back return socail...!");
        AuthStrategy client;
        AuthProviderEnum provider;

        switch (loginType.toUpperCase()) {
            case "GOOGLE" -> {
                client = googleAuthStrategy;
                provider = AuthProviderEnum.GOOGLE;
            }
            case "FACEBOOK" -> {
                client = facebookAuthStrategy;
                provider = AuthProviderEnum.FACEBOOK;
            }
            default -> throw new ResponseException(ResponseErrors.NOT_SUPPORT_AUTH_TYPE);
        }

        Map<String, Object> tokenData = client.exchangeCodeForToken(code);
        Map<String, Object> userInfo = client.getUserInfo(tokenData);
        String providerId = client.getProviderId(tokenData);

        String email = (String) userInfo.getOrDefault("email", "default-bookstore@gmail.com");
        String name = (String) userInfo.getOrDefault("name", "default");
        String picture = (String) userInfo.getOrDefault("picture", "default.png");

        Optional<User> existingUser = userRepository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        User user = userRepository.save(
                User.builder()
                        .email(email)
                        .authProvider(provider)
                        .providerId(providerId)
                        .roles(Set.of(roleRepository.getCustomerRole()))
                        .build()
        );

        userProfileRepository.save(
                UserProfile.builder()
                        .user(user)
                        .nickname(name)
                        .profileImage(picture)
                        .build()
        );

        return user;
    }
}
