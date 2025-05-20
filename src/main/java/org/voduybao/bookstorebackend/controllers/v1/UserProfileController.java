package org.voduybao.bookstorebackend.controllers.v1;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.AuthenDto;
import org.voduybao.bookstorebackend.services.auth.AuthenService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/profile")
@RequiredArgsConstructor
public class UserProfileController {
    @Setter(onMethod_ = @Autowired)
    private AuthenService authenService;

    @GetMapping("")
    public Result getMyProfile(@RequestBody @Validated AuthenDto.RegisterRequest request) {
        authenService.reigster(request);
        return Result.success();
    }

    @GetMapping("/user/{user_id}")
    public Result getProfile(@RequestBody @Validated AuthenDto.RegisterRequest request) {
        authenService.reigster(request);
        return Result.success();
    }

    @PutMapping("")
    public Result updateProfile(@RequestBody @Validated AuthenDto.LoginRequest request,
                         HttpServletResponse response) {
        var reponse = authenService.login(request, response);
        return Result.content(reponse);
    }

    @PutMapping("/avatar")
    public Result updateAvatar(@CookieValue(name = "refreshToken") String refreshToken,
                               HttpServletResponse response) {
        var reponse = authenService.refresh(refreshToken, response);
        return Result.content(reponse);
    }

}