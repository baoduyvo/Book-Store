package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.voduybao.bookstorebackend.tools.contants.GenderEnum;

public class UserProfileDto {
    @Getter
    @Setter
    public static class UpdateProfileRequest {
        String profileImage;

        @NotNull(message = "nickname is required")
        @NotEmpty
        String nickname;
        String firstName;
        String lastName;
        String intro;

        Boolean marry;

        @NotNull(message = "gender is required")
        GenderEnum gender;

        @NotNull(message = "Date is required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
        String birthday;

        Integer hobbyId;
        Integer jobId;
        Integer eduId;

        String address;
        String city;
        String state;
        String country;
        String postalCode;
    }

    @Getter
    @Setter
    public static class UpdateAvatarRequest {
        String profileImage;
    }
}
