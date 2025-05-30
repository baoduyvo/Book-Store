package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProducType(String producType);
}