package org.voduybao.bookstorebackend.services.user.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.user.Education;
import org.voduybao.bookstorebackend.dao.entities.user.Hobby;
import org.voduybao.bookstorebackend.dao.repositories.user.HobbyRepository;
import org.voduybao.bookstorebackend.dtos.HobbyDto;
import org.voduybao.bookstorebackend.services.user.HobbyService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j(topic = "HOBBY-SERVICE")
public class HobbyServiceImpl implements HobbyService {

    @Setter(onMethod_ = @Autowired)
    private HobbyRepository hobbyRepository;

    @Override
    public void create(HobbyDto.Request request) {
        log.info("Hobby create ...!");
        Hobby hobby = Hobby.builder()
                .title(request.getTitle())
                .build();
        hobbyRepository.save(hobby);
    }

    @Override
    public void update(Integer id, HobbyDto.Request request) {
        log.info("Hobby update ...!");
        Hobby hobby = hobbyRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.HOBBY_NOT_FOUND));
        hobby.setTitle(request.getTitle());
        hobbyRepository.save(hobby);
    }

    @Override
    public List<Hobby> list() {
        log.info("Hobby get lists ...!");
        List<Hobby> result = new ArrayList<>();
        for (Hobby hobby : hobbyRepository.findAll()) {
            result.add(hobby);
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        log.info("Hobby delete ...!");
        Hobby hobby = hobbyRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.HOBBY_NOT_FOUND));
        hobbyRepository.delete(hobby);
    }

    @Override
    public Hobby getById(Integer id) {
        log.info("Hobby get by id ...!");
        Hobby hobby = hobbyRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.HOBBY_NOT_FOUND));
        return hobby;
    }
}