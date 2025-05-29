package org.voduybao.bookstorebackend.mapper;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

@Component("CATEGORY-MAPPER")
public class CategoryMapper {
    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;

    public Category categoryMapDocumentById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));
    }

}