package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.OtpDto;
import org.voduybao.bookstorebackend.services.shared.OtpService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/otp")
@RequiredArgsConstructor
@Tag(name = "08 - Otp Controller", description = "API quản lý các bản ghi mã otp")
@Hidden
public class OtpController {
    @Setter(onMethod_ = @Autowired)
    private OtpService otpService;

    @PostMapping("/send")
    public Result sendOtp(@RequestBody @Validated OtpDto.Request request) {
        otpService.sendOtp(request);
        return Result.success();
    }

    @PostMapping("/verify")
    public Result verifyOtp(@RequestBody @Validated OtpDto.Request request) {
        otpService.verifyOtp(request);
        return Result.success();
    }

}