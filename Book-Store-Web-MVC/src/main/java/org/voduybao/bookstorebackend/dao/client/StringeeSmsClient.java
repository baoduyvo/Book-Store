package org.voduybao.bookstorebackend.dao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "stringee-sms", url = "https://api.stringee.com/v1")
public interface StringeeSmsClient {

    @PostMapping("/sms")
    Map<String, Object> sendSms(
            @RequestHeader("X-STRINGEE-AUTH") String jwtToken,
            @RequestBody Map<String, Object> payload
    );
}
