package org.voduybao.bookstorebackend.services.auth;

import org.voduybao.bookstorebackend.dao.entities.user.User;

public interface SocialAuthService {

    String baseUrl(String loginType);

    User handleCallback(String code, String loginType);
}
