package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.repository.CrudRepository;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {


}