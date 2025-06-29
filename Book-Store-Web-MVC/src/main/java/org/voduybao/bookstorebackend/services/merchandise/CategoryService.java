package org.voduybao.bookstorebackend.services.merchandise;

import org.voduybao.bookstorebackend.dtos.CategoryDto;

public interface CategoryService {

    void create(CategoryDto.CreatorRequest request);

    void update(Integer id, CategoryDto.UpdateParentRequest updateParentDto);

    void deleteChild(Integer id);
}
