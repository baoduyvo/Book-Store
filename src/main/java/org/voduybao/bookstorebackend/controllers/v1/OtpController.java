package org.voduybao.bookstorebackend.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.EducationDto;
import org.voduybao.bookstorebackend.dtos.OtpDto;
import org.voduybao.bookstorebackend.services.notification.EmailService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/otp")
@RequiredArgsConstructor
public class OtpController {
    @Setter(onMethod_ = @Autowired)
    private EmailService emailService;

    @PostMapping("/send")
    public Result sendOtp(@RequestBody @Validated OtpDto.Request request) {
        return Result.success();
    }

    @PostMapping("/verify-otp")
    public Result verifyOtp(@RequestBody @Validated EducationDto.Request request) {
        return Result.success();
    }

    @PostMapping("/resend-verify-otp")
    public Result resendVerifyOtp() {
        return Result.success();
    }
}