package org.voduybao.bookstorebackend.dao.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;

@Component
public class CategoryDataInitializer {

    private final CategoryRepository categoryRepository;

    public CategoryDataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initPermissions() {
        if (categoryRepository.count() == 0) {

        }
    }

    private void createPermission(String name, String description) {
        Category category = new Category();

        categoryRepository.save(category);
    }
}