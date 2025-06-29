package org.voduybao.bookstorebackend.services.merchandise;

import org.springframework.web.multipart.MultipartFile;
import org.voduybao.bookstorebackend.dao.entities.merchandise.MadeIn;
import org.voduybao.bookstorebackend.dtos.AuthorDto;
import org.voduybao.bookstorebackend.dtos.MadeInDto;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface AuthorService {
    void create(AuthorDto.CreatorRequest request, MultipartFile image);

    void delete(Integer id);

    void update(Integer id, MadeInDto.Request request);

    PaginationResult<MadeIn> listAndSearch(String keyword, Integer page, Integer size);

    MadeIn getById(Integer id);
}
