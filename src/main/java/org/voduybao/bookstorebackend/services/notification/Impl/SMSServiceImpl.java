package org.voduybao.bookstorebackend.services.notification.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.client.StringeeSmsClient;
import org.voduybao.bookstorebackend.services.notification.SMSService;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j(topic = "SMS")
public class SMSServiceImpl implements SMSService {

    @Setter(onMethod_ = @Autowired)
    private StringeeSmsClient stringeeSmsClient;

    @Override
    public void sendVerifyOtp(String phoneNumber, String otp) {
        String sms = String.format("%s là mã xác thực của bạn tại %s. Mã có hiệu lực trong %d phút. Không chia sẻ mã này với bất kỳ ai.", otp, "Book.vn", 5);
        // Tạo payload
        Map<String, Object> smsData = new HashMap<>();
        smsData.put("from", "");
        smsData.put("to", phoneNumber);
        smsData.put("text", sms);

        Map<String, Object> payload = new HashMap<>();
        payload.put("sms", new Map[]{smsData});

        // Gửi SMS qua FeignClient
        stringeeSmsClient.sendSms("abc", payload);
    }
}
