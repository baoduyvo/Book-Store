package org.voduybao.bookstorebackend.services.shared;

import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendVerifyOtp(@Size(max = 255) String email, String otp);

    void sendNewPassword(@Size(max = 255) String email, String newPassword);

}
