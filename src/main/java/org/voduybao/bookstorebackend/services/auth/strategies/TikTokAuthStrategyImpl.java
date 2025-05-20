//package org.voduybao.bookstorebackend.services.auth.strategies;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//@Slf4j(topic = "TIKTOK")
//public class TikTokAuthStrategyImpl implements TikTokAuthStrategy {
//    @Value("${spring.security.oauth2.client.registration.tiktok.client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.tiktok.client-secret}")
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.tiktok.redirect-uri}")
//    private String redirectUri;
//
//    @Value("${spring.security.oauth2.client.registration.tiktok.scope}")
//    private String scope;
//
//    @Override
//    public String verifyTikTokToken(String idToken) {
//        return "";
//    }
//
//    @Override
//    public String baseUrlTikTok() {
//        String baseUrl = "https://www.tiktok.com/v2/auth/authorize";
//        return baseUrl
//                + "?client_key=" + clientId
//                + "&redirect_uri=" + redirectUri
//                + "&response_type=code"
//                + "&scope=" + scope
//                + "&state=secureRandomStateString";
//    }
//
//    @Override
//    public Map<String, Object> exchangeCodeForToken(String code) {
//        return Map.of();
//    }
//
//    @Override
//    public Map<String, Object> getUserInfo(Map<String, Object> tokenResponse) {
//        return Map.of();
//    }
//
//    @Override
//    public String getProviderId(Map<String, Object> tokenResponse) {
//        return "";
//    }
//}
