package org.voduybao.bookstorebackend.mapper;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.common.embedded.Address;
import org.voduybao.bookstorebackend.dao.entities.user.Education;
import org.voduybao.bookstorebackend.dao.entities.user.Hobby;
import org.voduybao.bookstorebackend.dao.entities.user.Job;
import org.voduybao.bookstorebackend.dao.entities.user.UserProfile;
import org.voduybao.bookstorebackend.dao.repositories.user.EducationRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.HobbyRepository;
import org.voduybao.bookstorebackend.dao.repositories.user.JobRepository;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.Optional;

@Component
public class UserProfileMapper {

    @Setter(onMethod_ = @Autowired)
    private EducationRepository educationRepository;
    @Setter(onMethod_ = @Autowired)
    private HobbyRepository hobbyRepository;
    @Setter(onMethod_ = @Autowired)
    private JobRepository jobRepository;

    public void updateAddress(UserProfile profile, Address dto) {
        if (dto == null) return;
        Address address = Optional.ofNullable(profile.getAddress()).orElseGet(Address::new);

        Optional.ofNullable(dto.getAddress()).ifPresent(address::setAddress);
        Optional.ofNullable(dto.getCity()).ifPresent(address::setCity);
        Optional.ofNullable(dto.getState()).ifPresent(address::setState);
        Optional.ofNullable(dto.getCountry()).ifPresent(address::setCountry);
        Optional.ofNullable(dto.getPostalCode()).ifPresent(address::setPostalCode);

        profile.setAddress(address);
    }

    public void updateRelations(UserProfile profile, Integer hobbyId, Integer jobId, Integer eduId) {
        if (hobbyId != null) {
            Hobby hobby = hobbyRepository.findById(hobbyId)
                    .orElseThrow(() -> new ResponseException(ResponseErrors.HOBBY_NOT_FOUND));
            profile.setHobby(hobby);
        }

        if (jobId != null) {
            Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new ResponseException(ResponseErrors.JOB_NOT_FOUND));
            profile.setJob(job);
        }

        if (eduId != null) {
            Education education = educationRepository.findById(eduId)
                    .orElseThrow(() -> new ResponseException(ResponseErrors.EDUCATION_NOT_FOUND));
            profile.setEducation(education);
        }
    }
}