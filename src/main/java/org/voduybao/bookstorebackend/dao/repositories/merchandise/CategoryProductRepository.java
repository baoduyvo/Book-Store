package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.entities.merchandise.CategoryProduct;

public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Integer> {


}