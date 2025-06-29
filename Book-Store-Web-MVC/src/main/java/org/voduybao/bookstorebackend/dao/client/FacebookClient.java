package org.voduybao.bookstorebackend.dao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "facebookClient", url = "https://graph.facebook.com")
public interface FacebookClient {

    @GetMapping("/me")
    Map<String, Object> getUserInfo(
            @RequestParam("access_token") String accessToken,
            @RequestParam("fields") String fields
    );
}


