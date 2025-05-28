package org.voduybao.bookstorebackend.dao.initializer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryDataInitializer {

    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Map<String, Category> rootCategories = new HashMap<>();

            // Thêm các category gốc
            rootCategories.put("vanhoc", createCategory("Sách Văn Học", "Thể loại sách văn học"));
            rootCategories.put("kynang", createCategory("Sách Kỹ Năng", "Thể loại sách kỹ năng sống"));
            rootCategories.put("thieunhi", createCategory("Sách Thiếu Nhi", "Cho trẻ em và học sinh"));
            rootCategories.put("hoctap", createCategory("Sách Học Tập", "Giáo trình, sách tham khảo"));
            rootCategories.put("kinhte", createCategory("Sách Kinh Tế", "Kinh tế, tài chính, đầu tư"));

            // Thêm các category con
            createCategory("Tiểu Thuyết", "Sách văn học dạng tiểu thuyết", rootCategories.get("vanhoc"));
            createCategory("Truyện Ngắn", "Tuyển tập truyện ngắn", rootCategories.get("vanhoc"));

            createCategory("Giao Tiếp", "Kỹ năng giao tiếp hiệu quả", rootCategories.get("kynang"));
            createCategory("Quản Lý Thời Gian", "Kỹ năng quản lý thời gian", rootCategories.get("kynang"));

            createCategory("Sách Tô Màu", "Sách thiếu nhi tô màu", rootCategories.get("thieunhi"));
            createCategory("Truyện Cổ Tích", "Truyện cổ tích thiếu nhi", rootCategories.get("thieunhi"));

            createCategory("Toán Học", "Sách học tập Toán", rootCategories.get("hoctap"));
            createCategory("Văn Học", "Sách học Văn", rootCategories.get("hoctap"));

            createCategory("Đầu Tư", "Sách về đầu tư và tài chính", rootCategories.get("kinhte"));
            createCategory("Khởi Nghiệp", "Sách khởi nghiệp kinh doanh", rootCategories.get("kinhte"));
        }
    }

    private Category createCategory(String name, String description) {
        Category category = Category.builder()
                .name(name)
                .description(description)
                .archived(false)
                .build();
        return categoryRepository.save(category);
    }

    private Category createCategory(String name, String description, Category parent) {
        Category category = Category.builder()
                .name(name)
                .description(description)
                .parent(parent)
                .archived(false)
                .build();
        return categoryRepository.save(category);
    }
}
