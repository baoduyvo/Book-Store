package org.voduybao.bookstorebackend.services.auth.strategies;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.voduybao.bookstorebackend.dao.client.GoogleClient;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.Map;

@Component
@Slf4j(topic = "GOOGLE")
public class GoogleAuthStrategyImpl implements GoogleAuthStrategy {

    @Setter(onMethod_ = @Autowired)
    private GoogleClient googleClient;

    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String authorizationUri;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String scope;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String baseUrlGoogle() {
        return authorizationUri
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=" + scope
                + "&prompt=consent"
                + "&state=GOOGLE";
    }

    @Override
    public Map<String, Object> exchangeCodeForToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token", requestEntity, Map.class);

        if (response.getBody() == null) {
            throw new ResponseException(ResponseErrors.AUTH_INVALID, "Failed to get token from Google");
        }

        return response.getBody();
    }

    @Override
    public Map<String, Object> getUserInfo(Map<String, Object> tokenResponse) {
        String idToken = (String) tokenResponse.get("id_token");
        return googleClient.verifyToken(idToken);
    }

    @Override
    public String getProviderId(Map<String, Object> tokenResponse) {
        String idToken = (String) tokenResponse.get("id_token");
        return verifyGoogleToken(idToken);
    }

    public String verifyGoogleToken(String idToken) {
        try {
            Map<String, Object> response = googleClient.verifyToken(idToken);
            if (response != null && response.containsKey("sub")) {
                return response.get("sub").toString();
            } else {
                throw new ResponseException(ResponseErrors.AUTH_INVALID, "Invalid Google token");
            }
        } catch (Exception e) {
            throw new ResponseException(ResponseErrors.AUTH_INVALID, "Failed to verify Google token", e);
        }
    }
}
