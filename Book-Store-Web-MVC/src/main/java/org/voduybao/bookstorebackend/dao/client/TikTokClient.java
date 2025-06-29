//package org.voduybao.bookstorebackend.dao.client;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//import java.util.Map;
//
//@FeignClient(name = "tiktokClient", url = "https://open-api.tiktok.com")
//public interface TikTokClient {
//
//    @PostMapping("/oauth/userinfo/")
//    Map<String, Object> getUserInfo(@RequestHeader("Authorization") String authorization);
//}
