package org.voduybao.bookstorebackend.mapper;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CATEGORY-MAPPER")
public class CategoryMapper {
    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;

    public Map<Integer, Category> listParentCategory() {
        List<Category> allCategories = categoryRepository.findAll();
        Map<Integer, Category> categoryMap = new HashMap<>();
        for (Category category : allCategories) {
            if (category.getParent() != null) {
                categoryMap.put(category.getId(), category);
            }
        }
        return categoryMap;
    }

}