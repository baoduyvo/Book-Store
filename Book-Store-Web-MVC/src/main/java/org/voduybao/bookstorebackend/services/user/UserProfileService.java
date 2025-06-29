package org.voduybao.bookstorebackend.services.user;

import org.voduybao.bookstorebackend.dao.entities.user.UserProfile;
import org.voduybao.bookstorebackend.dtos.UserProfileDto;

public interface UserProfileService {

    UserProfile myProfile(int userId);

    void updateProfile(int userID, UserProfileDto.UpdateProfileRequest request);

    void updateAvater(int userID, UserProfileDto.UpdateAvatarRequest request);

}
