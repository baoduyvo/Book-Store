package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.ProductBundleDto;
import org.voduybao.bookstorebackend.services.merchandise.ProductBundleService;
import org.voduybao.bookstorebackend.tools.contants.a.AdminRequired;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/product/bundles")
@RequiredArgsConstructor
@Tag(name = "10 - Product Bundle Controller", description = "API quản lý các bản ghi gói sản phẩm")
public class ProductBundleController {

    @Setter(onMethod_ = @Autowired)
    private ProductBundleService productBundleService;

    @AdminRequired
    @PostMapping("")
    @Operation(summary = "create product bundle", description = "tạo các bản ghi về gói sản phẩm")
    public Result createBookBundle(@Validated @RequestBody ProductBundleDto.ProductBundleRequest request) {
        productBundleService.create(request);
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @Operation(summary = "delete product bundle", description = "xóa bản ghi về gói sản phẩm chỉ chuyển trạng thái thành false")
    public Result deleteCombo(@PathVariable("id") Integer bundleId) {
        productBundleService.delete(bundleId);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}/active")
    @Operation(summary = "update status product bundle", description = "cập nhật bản ghi về gói sản phẩm chuyển trạng thái")
    public Result isActiveBookBundle(@PathVariable("id") Integer bundleId,
                                     @Validated @RequestBody ProductBundleDto.ProductBundleIsActiveRequest request) {
        productBundleService.isActiveProductBundle(bundleId, request);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}/release-date")
    @Operation(summary = "update release date product bundle", description = "cập nhật bản ghi về gói sản phẩm ngày phát hành")
    public Result setReleaseDate(@PathVariable("id") Integer bundleId,
                                 @Validated @RequestBody ProductBundleDto.ReleaseDateRequest request) {
        productBundleService.updateReleaseDate(bundleId, request);
        return Result.success();
    }

    @GetMapping("")
    @Operation(summary = "list product bundle search keyword", description = "danh sách bản ghi về gói sản phẩm và tìm kiếm theo từ khóa")
    public Result listAndSearch(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        var reponse = productBundleService.listAndSearch(keyword, page, size);
        return Result.content(reponse);
    }
}
