package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.ProductBundle;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.ProductBundleRepository;
import org.voduybao.bookstorebackend.dtos.ProductBundleDto;
import org.voduybao.bookstorebackend.services.merchandise.ProductBundleService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static org.voduybao.bookstorebackend.tools.utils.DateUtils.parsePublicationDate;
import static org.voduybao.bookstorebackend.tools.utils.StrUtil.FORMAT_DATE_AND_TIME;

@Component
@Slf4j(topic = "PRODUCT-BUNDLE-SERVICE")
public class ProductBundleServiceImpl implements ProductBundleService {

    @Setter(onMethod_ = @Autowired)
    private ProductBundleRepository productBundleRepository;

    @Override
    public void create(ProductBundleDto.ProductBundleRequest request) {
        log.info("Product Bundle created ...!");
        ProductBundle bundle = ProductBundle.builder()
                .name(request.getName())
                .price(request.getPrice())
                .discountPercent(request.getDiscountPercent())
                .description(request.getDescription())
                .releaseDate(Instant.now())
                .build();
        productBundleRepository.save(bundle);
    }

    @Override
    public void delete(Integer id) {
        log.info("Product Bundle delete ...!");
        if (!productBundleRepository.existsById(id)) {
            throw new ResponseException(ResponseErrors.NOT_FOUND_ID_BOOK_BUNDLE);
        }
        productBundleRepository.updateProductBundle(id);
    }

    @Override
    public void isActiveProductBundle(Integer id, ProductBundleDto.ProductBundleIsActiveRequest request) {
        log.info("Product Bundle update is active ...!");
        if (!productBundleRepository.existsById(id)) {
            throw new ResponseException(ResponseErrors.NOT_FOUND_ID_BOOK_BUNDLE);
        }
        productBundleRepository.updateProductBundlesActive(id, request.getIsActive());
    }

    @Override
    public void updateReleaseDate(Integer id, ProductBundleDto.ReleaseDateRequest request) {
        log.info("Product Bundle update release date ...!");
        if (!productBundleRepository.existsById(id)) {
            throw new ResponseException(ResponseErrors.NOT_FOUND_ID_BOOK_BUNDLE);
        }
        productBundleRepository.updateProductBundlesReleaseDate(id, parsePublicationDate(request.getReleaseDate(), FORMAT_DATE_AND_TIME));
    }

    @Override
    public PaginationResult<ProductBundle> listAndSearch(String keyword, Integer page, Integer size) {
        PaginationUtils.PaginationResult pagination = PaginationUtils.validateAndConvert(page, size);

        if (Objects.isNull(keyword) || keyword.isEmpty()) {
            keyword = null;
        } else {
            keyword = keyword.trim();
        }

        List<ProductBundle> results = productBundleRepository.findProductBundles(keyword, pagination.pageSize(), pagination.offset());

        int count = 0;
        count = (int) Math.min(productBundleRepository.count(), Integer.MAX_VALUE);

        return new PaginationResult<>(count, results, page, size);
    }
}
