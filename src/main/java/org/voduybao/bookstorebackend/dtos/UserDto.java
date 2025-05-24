package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.voduybao.bookstorebackend.dao.repositories.user.join.UserUserProfileJoin;

public class UserDto {

    @Getter
    @Setter
    public static class ChangePasswordRequest {
        @NotNull
        @NotEmpty
        @Size(min = 6, max = 20)
        String oldPassword;
        @NotNull
        @NotEmpty
        @Size(min = 6, max = 20)
        String newPassword;
    }


    @Getter
    public static class ForgotPasswordRequest {
        @NotNull(message = "Vui Lòng Nhập Số Điện Thoại Hoặc Email")
        private String phoneOrEmail;

        public boolean isPhone() {
            return phoneOrEmail.matches("^(\\+?[0-9]{1,4})?([0-9]{10})$");
        }

        public boolean isEmail() {
            return phoneOrEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }
    }

    @Data
    public class ConfirmForgotPasswordRequest {
        private String otp;
        private String newPassword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UsersResponse {
        private Integer userId;
        private String email;
        private String phoneNumber;
        private String authProvider;
        private String providerId;
        private Boolean isActive;
        private String nickname;
        private String intro;
        private String gender;
        private String firstName;
        private String lastName;
        private String hobbyTitle;
        private String jobTitle;
        private String eduTitle;
        private String jti;
        private String roleName;

        public static UsersResponse fromEntity(UserUserProfileJoin entity) {
            return new UsersResponse(
                    entity.getUserId(),
                    entity.getEmail(),
                    entity.getPhoneNumber(),
                    entity.getAuthProvider(),
                    entity.getProviderId(),
                    entity.getIsActive(),
                    entity.getNickname(),
                    entity.getIntro(),
                    entity.getGender(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getJobId(),
                    entity.getHobbyId(),
                    entity.getEduId(),
                    entity.getJti(),
                    entity.getRoleName()
            );
        }
    }
}
