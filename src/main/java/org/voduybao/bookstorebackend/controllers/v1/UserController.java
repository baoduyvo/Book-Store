package org.voduybao.bookstorebackend.controllers.v1;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.UserDto;
import org.voduybao.bookstorebackend.services.user.UserService;
import org.voduybao.bookstorebackend.tools.http.Headers;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @GetMapping("")
    public Result getUsers(
            @RequestParam(value = "page") @Min(0) int page,
            @RequestParam(value = "size") @Min(0) @Max(50) int size
    ) {
        var reponse = userService.findAllUser(page, size);
        return Result.content(reponse);
    }

    @DeleteMapping("")
    public Result deleteAccount(@RequestHeader(value = Headers.X_USER_ID) int userID) {
        userService.deleteAccount(userID);
        return Result.success();
    }

    @PutMapping("/change-password")
    public Result changePassword(
            @RequestHeader(value = Headers.X_USER_ID) int userID,
            @Validated @RequestBody UserDto.ChangePasswordRequest request) {
        userService.changePassword(userID, request);
        return Result.success();
    }

    @PostMapping("/forgot-password")
    public Result forgotPassword(@Validated @RequestBody UserDto.ForgotPasswordRequest request) {
        userService.forgotPassword(request);
        return Result.success();
    }

    @PostMapping("/confirm-password")
    public Result confirmPassword(
            @RequestHeader(value = Headers.X_USER_ID) int userID,
            @Validated @RequestBody UserDto.ConfirmForgotPasswordRequest request) {
        userService.confirmPassword(userID, request);
        return Result.success();
    }

    @PutMapping("/reset-password/{user_id}")
    public Result resetPassword(@PathVariable("user_id") @NotNull Integer userId){
        return Result.success();
    }

}