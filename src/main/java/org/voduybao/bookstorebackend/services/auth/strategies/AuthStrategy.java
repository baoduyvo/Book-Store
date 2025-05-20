package org.voduybao.bookstorebackend.services.auth.strategies;

import java.util.Map;

public interface AuthStrategy {
    Map<String, Object> exchangeCodeForToken(String code);
    Map<String, Object> getUserInfo(Map<String, Object> tokenResponse);
    String getProviderId(Map<String, Object> tokenResponse);
}
