package org.voduybao.bookstorebackend.dao.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Product;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.ProductRepository;

@Component
@RequiredArgsConstructor
@Order(1)
public class ProductDataInitializer implements ApplicationRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (productRepository.count() == 0) {
            createProduct("Book");
            createProduct("Stationery");
        }
    }

    private Product createProduct(String type) {
        Product product = Product.builder()
                .producType(type)
                .build();
        return productRepository.save(product);
    }

}
