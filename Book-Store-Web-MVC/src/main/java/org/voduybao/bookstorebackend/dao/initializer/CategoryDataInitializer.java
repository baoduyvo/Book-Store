package org.voduybao.bookstorebackend.dao.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.entities.merchandise.CategoryProduct;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Product;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryProductRepository;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.ProductRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Order(2)
public class CategoryDataInitializer implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryProductRepository categoryProductRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (categoryRepository.count() == 0) {
            Product bookProduct = productRepository.findByProducType("Book");
            Product stationeryProduct = productRepository.findByProducType("Stationery");

            Map<String, Category> rootCategories = new HashMap<>();

            // Thêm các category gốc
            rootCategories.put("vanhoc", createCategory("Sách Văn Học", "Thể loại sách văn học", bookProduct));
            rootCategories.put("kynang", createCategory("Sách Kỹ Năng", "Thể loại sách kỹ năng sống", bookProduct));
            rootCategories.put("thieunhi", createCategory("Sách Thiếu Nhi", "Cho trẻ em và học sinh", bookProduct));
            rootCategories.put("hoctap", createCategory("Sách Học Tập", "Giáo trình, sách tham khảo", bookProduct));
            rootCategories.put("kinhte", createCategory("Sách Kinh Tế", "Kinh tế, tài chính, đầu tư", bookProduct));

            rootCategories.put("but", createCategory("Bút Viết", "Các loại bút: bi, chì, mực, máy", stationeryProduct));
            rootCategories.put("vo", createCategory("Vở - Sổ Tay", "Vở học sinh, sổ ghi chép, sổ tay cá nhân", stationeryProduct));
            rootCategories.put("thuoc", createCategory("Thước Kẻ", "Thước học sinh, thước kỹ thuật, eke", stationeryProduct));
            rootCategories.put("tay", createCategory("Tẩy - Gôm", "Gôm tẩy chì, tẩy mực, tiện dụng cho học sinh", stationeryProduct));
            rootCategories.put("dungcu", createCategory("Dụng Cụ Văn Phòng", "Kẹp giấy, dao rọc giấy, ghim, băng keo", stationeryProduct));

            // Thêm các category con về sách
            createCategory("Tiểu Thuyết", "Sách văn học dạng tiểu thuyết", rootCategories.get("vanhoc"), bookProduct);
            createCategory("Truyện Ngắn", "Tuyển tập truyện ngắn", rootCategories.get("vanhoc"), bookProduct);
            createCategory("Giao Tiếp", "Kỹ năng giao tiếp hiệu quả", rootCategories.get("kynang"), bookProduct);
            createCategory("Quản Lý Thời Gian", "Kỹ năng quản lý thời gian", rootCategories.get("kynang"), bookProduct);
            createCategory("Sách Tô Màu", "Sách thiếu nhi tô màu", rootCategories.get("thieunhi"), bookProduct);
            createCategory("Truyện Cổ Tích", "Truyện cổ tích thiếu nhi", rootCategories.get("thieunhi"), bookProduct);
            createCategory("Toán Học", "Sách học tập Toán", rootCategories.get("hoctap"), bookProduct);
            createCategory("Văn Học", "Sách học Văn", rootCategories.get("hoctap"), bookProduct);
            createCategory("Đầu Tư", "Sách về đầu tư và tài chính", rootCategories.get("kinhte"), bookProduct);
            createCategory("Khởi Nghiệp", "Sách khởi nghiệp kinh doanh", rootCategories.get("kinhte"), bookProduct);

            // Thêm các category con cho Văn phòng phẩm
            createCategory("Bút Bi", "Các loại bút bi dùng hằng ngày", rootCategories.get("but"), stationeryProduct);
            createCategory("Bút Chì", "Bút chì gỗ, chì bấm dùng cho học sinh và văn phòng", rootCategories.get("but"), stationeryProduct);
            createCategory("Sổ Tay Có Dòng", "Sổ tay có dòng, tiện ghi chú", rootCategories.get("vo"), stationeryProduct);
            createCategory("Sổ Tay Trơn", "Sổ tay không dòng, dùng cho vẽ hoặc ghi chép tự do", rootCategories.get("vo"), stationeryProduct);
            createCategory("Thước Thẳng", "Thước nhựa, kim loại dùng học tập", rootCategories.get("thuoc"), stationeryProduct);
            createCategory("Eke - Thước Vuông", "Bộ eke dành cho hình học", rootCategories.get("thuoc"), stationeryProduct);
            createCategory("Gôm Tẩy Chì", "Tẩy dành cho viết chì", rootCategories.get("tay"), stationeryProduct);
            createCategory("Gôm Tẩy Mực", "Tẩy dành cho viết mực", rootCategories.get("tay"), stationeryProduct);
            createCategory("Dao Rọc Giấy", "Dao tiện dụng dùng cắt giấy", rootCategories.get("dungcu"), stationeryProduct);
            createCategory("Băng Keo", "Băng keo trong, keo giấy, keo hai mặt", rootCategories.get("dungcu"), stationeryProduct);

        }
    }

    private Category createCategory(String name, String description, Product product) {
        Category category = Category.builder()
                .name(name)
                .description(description)
                .archived(false)
                .build();

        category = categoryRepository.save(category);

        categoryProductRepository.save(CategoryProduct.builder()
                .category(category)
                .product(product)
                .build());

        return category;
    }

    private Category createCategory(String name, String description, Category parent, Product product) {
        Category category = Category.builder()
                .name(name)
                .description(description)
                .parent(parent)
                .archived(false)
                .build();

        category = categoryRepository.save(category);

        categoryProductRepository.save(CategoryProduct.builder()
                .category(category)
                .product(product)
                .build());

        return category;
    }
}
