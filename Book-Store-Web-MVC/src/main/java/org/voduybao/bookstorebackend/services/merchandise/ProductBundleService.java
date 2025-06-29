package org.voduybao.bookstorebackend.services.merchandise;

import org.voduybao.bookstorebackend.dao.entities.merchandise.ProductBundle;
import org.voduybao.bookstorebackend.dtos.ProductBundleDto;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface ProductBundleService {
    void create(ProductBundleDto.ProductBundleRequest request);

    void delete(Integer id);

    void isActiveProductBundle(Integer id, ProductBundleDto.ProductBundleIsActiveRequest request);

    void updateReleaseDate(Integer id, ProductBundleDto.ReleaseDateRequest request);

    PaginationResult<ProductBundle> listAndSearch(String keyword, Integer page, Integer size);
}
