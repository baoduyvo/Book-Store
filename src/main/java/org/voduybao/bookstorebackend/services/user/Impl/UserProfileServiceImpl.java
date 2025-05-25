package org.voduybao.bookstorebackend.services.user.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.UserProfile;
import org.voduybao.bookstorebackend.dao.repositories.user.UserProfileRepository;
import org.voduybao.bookstorebackend.dtos.UserProfileDto;
import org.voduybao.bookstorebackend.mapper.UserProfileMapper;
import org.voduybao.bookstorebackend.services.user.UserProfileService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.Optional;

@Component
@Slf4j(topic = "USER-PROFLIE-SERVICE")
public class UserProfileServiceImpl implements UserProfileService {

    @Setter(onMethod_ = @Autowired)
    private UserProfileRepository userProfileRepository;
    @Setter(onMethod_ = @Autowired)
    private UserProfileMapper userProfileMapper;

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

        Optional.ofNullable(request.getFirstName()).ifPresent(profile::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(profile::setLastName);
        Optional.ofNullable(request.getNickname()).ifPresent(profile::setNickname);
        Optional.ofNullable(request.getIntro()).ifPresent(profile::setIntro);
        Optional.ofNullable(request.getMarry()).ifPresent(profile::setMarry);
        Optional.ofNullable(request.getGender()).ifPresent(profile::setGender);

        userProfileMapper.updateRelations(profile, request.getHobbyId(), request.getJobId(), request.getEduId());
        userProfileMapper.updateAddress(profile, request.getAddress());

        userProfileRepository.save(profile);
    }

    @Override
    public void updateAvater(int userID, UserProfileDto.UpdateAvatarRequest request) {
        UserProfile profile = userProfileRepository.findById(userID)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_PROFILE));

    }
}