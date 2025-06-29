package org.voduybao.bookstorebackend.services.merchandise;

import org.voduybao.bookstorebackend.dao.entities.merchandise.MadeIn;
import org.voduybao.bookstorebackend.dtos.MadeInDto;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface MadeInService {
    void create(MadeInDto.Request request);

    void delete(Integer id);

    void update(Integer id, MadeInDto.Request request);

    PaginationResult<MadeIn> listAndSearch(String keyword, Integer page, Integer size);

    MadeIn getById(Integer id);
}
