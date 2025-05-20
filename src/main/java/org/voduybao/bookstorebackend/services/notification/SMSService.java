package org.voduybao.bookstorebackend.services.notification;

import jakarta.validation.constraints.Size;

public interface SMSService {
    void sendVerifyOtp(@Size(max = 20) String phoneNumber, String otp);
}
