package org.voduybao.bookstorebackend.services.user.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.UserProfile;
import org.voduybao.bookstorebackend.dao.repositories.user.UserProfileRepository;
import org.voduybao.bookstorebackend.dtos.UserProfileDto;
import org.voduybao.bookstorebackend.services.user.UserProfileService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

@Component
@Slf4j(topic = "USER-PROFLIE-SERVICE")
public class UserProfileServiceImpl implements UserProfileService {

    @Setter(onMethod_ = @Autowired)
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile myProfile(int userID) {
        UserProfile profile = userProfileRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_PROFILE));
        return profile;
    }

    @Override
    public void updateProfile(int userID, UserProfileDto.UpdateProfileRequest request) {
        UserProfile profile = userProfileRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_PROFILE));
        userProfileRepository.save(profile);
    }

    @Override
    public void updateAvater(int userID, UserProfileDto.UpdateAvatarRequest request) {
        UserProfile profile = userProfileRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_PROFILE));
        userProfileRepository.updateAvatar(request.getProfileImage(), userID);
    }
}