package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/authentication")
@RequiredArgsConstructor
@Tag(name = "01 - Authen Controller", description = "API quản lý các xác thực của người dùng")
public class AuthController {
    @Setter(onMethod_ = @Autowired)
    private AuthenService authenService;

    @PostMapping("/register")
    @Hidden
    public Result register(@RequestBody @Validated AuthenDto.RegisterRequest request) {
        authenService.reigster(request);
        return Result.success();
    }

    @PostMapping("/sign-in")
    public Result signIn(@RequestBody @Validated AuthenDto.LoginRequest request,
                         HttpServletResponse response) {
        var reponse = authenService.login(request, response);
        return Result.content(reponse);
    }

    @PostMapping("/refresh-token")
    @Hidden
    public Result refreshToken(@CookieValue(name = "refreshToken") String refreshToken,
                               HttpServletResponse response) {
        var reponse = authenService.refresh(refreshToken, response);
        return Result.content(reponse);
    }

    @PostMapping("/sign-out")
    @Hidden
    public Result signOut(@RequestBody @Validated AuthenDto.TokenRequest request,
                          HttpServletResponse response) {
        authenService.logout(request, response);
        return Result.success();
    }

    @PostMapping("/introspect")
    @Hidden
    public Result introspect(@RequestBody @Validated AuthenDto.TokenRequest request) {
        var reponse = authenService.introspect(request);
        return Result.content(reponse);
    }

    @GetMapping("/socail-login")
    @Hidden
    public Result socailAuth(@RequestParam("login_type") String loginType) {
        var reponse = authenService.socailAuthType(loginType);
        return Result.content(reponse);
    }

    @GetMapping("/callback")
    @Hidden
    public Result callback(@RequestParam("code") String code,
                           @RequestParam("state") String loginType,
                           HttpServletResponse response) {
        var reponse = authenService.callback(code, loginType, response);
        return Result.content(reponse);
    }

}