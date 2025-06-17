package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.MadeInDto;
import org.voduybao.bookstorebackend.services.merchandise.MadeInService;
import org.voduybao.bookstorebackend.tools.contants.a.AdminRequired;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/product/trade-makes")
@RequiredArgsConstructor
@Tag(name = "12 - Product Trade Make Controller", description = "API quản lý các sản phẩm có nhãn hiệu thương mại")
@Hidden
public class TradeMarkController {

    @Setter(onMethod_ = @Autowired)
    private MadeInService madeInService;

    @AdminRequired
    @PostMapping("")
    @Operation(summary = "create trade make", description = "tạo các bản ghi về nhãn hiệu")
    public Result createBookBundle(@Validated @RequestBody MadeInDto.Request request) {
        madeInService.create(request);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}")
    @Operation(summary = "put trade make", description = "sửa các bản ghi nhãn hiệu")
    public Result update(@PathVariable("id") Integer id,
                         @RequestBody @Validated MadeInDto.Request request) {
        madeInService.update(id, request);
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @Operation(summary = "delete trade make", description = "xóa các bản ghi nhãn hiệu")
    public Result delete(@PathVariable("id") Integer id) {
        madeInService.delete(id);
        return Result.success();
    }

    @GetMapping("")
    @Operation(summary = "list trade make and search keyword",
            description = "danh sách bản ghi về nhãn hiệu và tìm kiếm theo từ khóa")
    public Result listAndSearch(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        var reponse = madeInService.listAndSearch(keyword, page, size);
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get trade make by id", description = "lấy nhãn hiệu theo id")
    public Result getById(@PathVariable int id) {
        var reponse = madeInService.getById(id);
        return Result.content(reponse);
    }
}
