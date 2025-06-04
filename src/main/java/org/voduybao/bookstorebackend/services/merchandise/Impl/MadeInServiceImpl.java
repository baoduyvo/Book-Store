package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.MadeIn;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.MadeInRepository;
import org.voduybao.bookstorebackend.dtos.MadeInDto;
import org.voduybao.bookstorebackend.services.merchandise.MadeInService;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

@Component
@Slf4j(topic = "PRODUCT-MADE-IN-SERVICE")
public class MadeInServiceImpl implements MadeInService {

    @Setter(onMethod_ = @Autowired)
    private MadeInRepository madeInRepository;

    @Override
    public void create(MadeInDto.Request request) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, MadeInDto.Request request) {

    }

    @Override
    public PaginationResult<MadeIn> listAndSearch(String keyword, Integer page, Integer size) {
        return null;
    }

    @Override
    public MadeIn getById(Integer id) {
        return null;
    }
}
