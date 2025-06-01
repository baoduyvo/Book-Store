package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.UserProfileDto;
import org.voduybao.bookstorebackend.services.user.UserProfileService;
import org.voduybao.bookstorebackend.tools.http.Headers;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/profile")
@RequiredArgsConstructor
@Tag(name = "Education Controller", description = "API quản lý các bản ghi thông tin của người dùng")
public class UserProfileController {
    @Setter(onMethod_ = @Autowired)
    private UserProfileService userProfileService;

    @GetMapping("")
    public Result getMyProfile(@RequestHeader(value = Headers.X_USER_ID) int userID) {
        var reponse = userProfileService.myProfile(userID);
        return Result.content(reponse);
    }

    @PutMapping("")
    public Result updateProfile(
            @RequestHeader(value = Headers.X_USER_ID) int userID,
            @RequestBody @Validated UserProfileDto.UpdateProfileRequest request) {
        userProfileService.updateProfile(userID, request);
        return Result.success();
    }

    @PutMapping("/avatar")
    public Result updateAvatar(
            @RequestHeader(value = Headers.X_USER_ID) int userID,
            @RequestBody @Validated UserProfileDto.UpdateAvatarRequest request
    ) {
        userProfileService.updateAvater(userID, request);
        return Result.success();
    }

}