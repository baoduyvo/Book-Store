package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.voduybao.bookstorebackend.dao.entities.embedded.Address;
import org.voduybao.bookstorebackend.tools.contants.e.GenderEnum;

public class UserProfileDto {
    @Getter
    @Setter
    public static class UpdateProfileRequest {
        String nickname;
        String firstName;
        String lastName;
        String intro;
        Boolean marry;
        GenderEnum gender;
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Invalid date format. Expected format: yyyy-MM-dd")
        String birthday;

        Integer hobbyId;
        Integer jobId;
        Integer eduId;

        @Getter
        Address address;
    }

    @Getter
    @Setter
    public static class UpdateAvatarRequest {
        int profileImageId;
    }
}
