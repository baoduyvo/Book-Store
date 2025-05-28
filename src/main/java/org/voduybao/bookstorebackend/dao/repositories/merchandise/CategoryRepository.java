package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select category_id from categories where category_id = :id", nativeQuery = true)
    Integer checkExistIdBy(@Param("id") Integer id);

    @Query(nativeQuery = true, value = """
            SELECT * FROM categories
                        WHERE lower(name) = lower(:name) and (:id is null or category_id != :id)
            """)
    List<Category> findByName(@Param("name") String name, @Param("id") Integer id);

    @Query(value = "select * from categories where category_id = :id", nativeQuery = true)
    Category findCategoriesBy(@Param("id") Integer id);

    @Query(value = "select category_id from categories where parent_id = :id limit 1", nativeQuery = true)
    Integer checkExistParentId(@Param("id") Integer id);

    @Query(value = "select * from categories where parent_id = :id limit 1", nativeQuery = true)
    Category findFirstByParentId(@Param("id") Integer parentId);

    @Query(nativeQuery = true, value = """
            SELECT * FROM  categories c WHERE parent_id IS NOT NULL AND archived = false
            LIMIT :limit 
            OFFSET :offset
            """)
    List<Category> findFirstByParentIdIsNotNull(@Param("limit") Integer limit,
                                                @Param("offset") Integer offset);

    @Query(nativeQuery = true, value = """
            SELECT COUNT(*) FROM categories WHERE parent_id IS NOT NULL
            """)
    long count();
}