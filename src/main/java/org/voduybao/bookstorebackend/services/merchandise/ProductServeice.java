package org.voduybao.bookstorebackend.services.merchandise;

import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dtos.CategoryDto;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface ProductServeice {

    PaginationResult<Category> list(int page, int size);

    void create(CategoryDto.CreatorRequest request);

    void update(Integer id, CategoryDto.UpdateParentRequest updateParentDto);

    void deleteChild(Integer id);
}
