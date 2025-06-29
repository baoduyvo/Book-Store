package org.voduybao.bookstorebackend.dao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "googleClient", url = "https://www.googleapis.com")
public interface GoogleClient {

    @GetMapping("/oauth2/v3/tokeninfo")
    Map<String, Object> verifyToken(@RequestParam("id_token") String idToken);
}
