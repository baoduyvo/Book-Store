package org.voduybao.bookstorebackend.services.auth;

import org.voduybao.bookstorebackend.dao.entities.User;

public interface SocialAuthService {

    String baseUrl(String loginType);

    User handleCallback(String code, String loginType);
}
