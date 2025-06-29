package org.voduybao.bookstorebackend.services.auth.strategies;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.voduybao.bookstorebackend.dao.client.FacebookClient;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j(topic = "FACEBOOK")
public class FacebookAuthStrategyImpl implements FacebookAuthStrategy {

    @Setter(onMethod_ = @Autowired)
    private FacebookClient facebookClient;

    @Value("${spring.security.oauth2.client.provider.facebook.authorization-uri}")
    private String authorizationUri;
    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.facebook.scope}")
    private String scope;
    @Value("${spring.security.oauth2.client.registration.facebook.redirect-uri}")
    private String redirectUri;
    private final String tokenUri = "https://graph.facebook.com/v12.0/oauth/access_token";

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String baseUrlFacebook() {
        return authorizationUri
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=" + scope
                + "&state=FACEBOOK";
    }

    @Override
    public Map<String, Object> exchangeCodeForToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("redirect_uri", redirectUri);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                tokenUri, requestEntity, Map.class);

        Map<String, Object> body = response.getBody();
        if (body == null || !body.containsKey("access_token"))
            throw new ResponseException(ResponseErrors.AUTH_INVALID, "Failed to get access token from Facebook");


        return body;
    }

    @Override
    public Map<String, Object> getUserInfo(Map<String, Object> tokenResponse) {
        String token = (String) tokenResponse.get("access_token");
        Map<String, Object> rawUserInfo = facebookClient.getUserInfo(token, "id,name,email,picture");
        Map<String, Object> result = new HashMap<>();
        result.put("email", rawUserInfo.get("email"));
        result.put("name", rawUserInfo.get("name"));
        String pictureUrl = "default.png";
        if (rawUserInfo.containsKey("picture")) {
            try {
                Map<String, Object> picMap = (Map<String, Object>) rawUserInfo.get("picture");
                Map<String, Object> dataMap = (Map<String, Object>) picMap.get("data");
                pictureUrl = (String) dataMap.getOrDefault("url", pictureUrl);
            } catch (Exception ignored) {
            }
        }
        result.put("picture", pictureUrl);

        return result;
    }

    @Override
    public String getProviderId(Map<String, Object> tokenResponse) {
        String token = (String) tokenResponse.get("access_token");
        return verifyFacebookToken(token);
    }

    public String verifyFacebookToken(String accessToken) {
        try {
            Map<String, Object> response = facebookClient.getUserInfo(accessToken, "id,name,email,picture");
            if (response != null && response.containsKey("id")) {
                return response.get("id").toString();
            } else {
                throw new ResponseException(ResponseErrors.AUTH_INVALID, "Invalid Facebook token");
            }
        } catch (Exception e) {
            throw new ResponseException(ResponseErrors.AUTH_INVALID, "Failed to verify Facebook token", e);
        }
    }
}
