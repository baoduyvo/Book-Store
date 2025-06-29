package org.voduybao.bookstorebackend.dao.repositories.merchandise;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.merchandise.ProductBundle;

import java.time.Instant;
import java.util.List;

public interface ProductBundleRepository extends JpaRepository<ProductBundle, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            UPDATE product_bundles SET is_active = true WHERE bundle_id = :id
            """)
    void updateProductBundle(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            UPDATE product_bundles SET is_active = :status WHERE bundle_id = :id
            """)
    void updateProductBundlesActive(@Param("id") Integer id, @Param("status") Boolean status);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            UPDATE product_bundles SET release_date = :releaseDate WHERE bundle_id = :id
            """)
    void updateProductBundlesReleaseDate(@Param("id") Integer id, @Param("releaseDate") Instant releaseDate);

    @Query(nativeQuery = true, value = """
            SELECT * FROM product_bundles
            WHERE (:keyword IS NULL OR :keyword = '' OR name LIKE CONCAT('%', :keyword, '%')) 
            LIMIT :limit OFFSET :offset
            """)
    List<ProductBundle> findProductBundles(@Param("keyword") String keyword,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
}
