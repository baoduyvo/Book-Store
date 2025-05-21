package org.voduybao.bookstorebackend.services.user.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.Education;
import org.voduybao.bookstorebackend.dao.repositories.user.EducationRepository;
import org.voduybao.bookstorebackend.dtos.EducationDto;
import org.voduybao.bookstorebackend.services.user.EducationService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j(topic = "EDUCATION-SERVICE")
public class EducationServiceImpl implements EducationService {

    @Setter(onMethod_ = @Autowired)
    private EducationRepository educationRepository;

    @Override
    public void create(EducationDto.Request request) {
        Education education = Education.builder()
                .title(request.getTitle())
                .build();
        educationRepository.save(education);
    }

    @Override
    public void update(Integer id, EducationDto.Request request) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.EDUCATION_NOT_FOUND));
        education.setTitle(request.getTitle());
        educationRepository.save(education);
    }

    @Override
    public List<Education> list() {
        List<Education> result = new ArrayList<>();
        for (Education education : educationRepository.findAll()) {
            result.add(education);
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        educationRepository.deleteById(id);
    }

    @Override
    public Education getById(Integer id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.EDUCATION_NOT_FOUND));
        return education;
    }
}