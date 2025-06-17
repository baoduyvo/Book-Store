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
@RequestMapping("/v1/product/mades-in")
@RequiredArgsConstructor
@Tag(name = "11 - Product Made In Controller", description = "API quản lý các sản phẩm được sản xuất tại")
@Hidden
public class MadeInController {

    @Setter(onMethod_ = @Autowired)
    private MadeInService madeInService;

    @AdminRequired
    @PostMapping("")
    @Operation(summary = "create made in", description = "tạo các bản ghi về thực hiện tại")
    public Result createBookBundle(@Validated @RequestBody MadeInDto.Request request) {
        madeInService.create(request);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}")
    @Operation(summary = "put made in", description = "sửa các bản ghi nhà sản xuất")
    public Result update(@PathVariable("id") Integer id,
                         @RequestBody @Validated MadeInDto.Request request) {
        madeInService.update(id, request);
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @Operation(summary = "delete made in", description = "xóa các bản ghi nhà sản xuất")
    public Result delete(@PathVariable("id") Integer id) {
        madeInService.delete(id);
        return Result.success();
    }

    @GetMapping("")
    @Operation(summary = "list made in and search keyword", description = "danh sách bản ghi về nhà sản xuất và tìm kiếm theo từ khóa")
    public Result listAndSearch(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        var reponse = madeInService.listAndSearch(keyword, page, size);
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get made in by id", description = "Lấy nhà sản xuất theo id")
    public Result getById(@PathVariable int id) {
        var reponse = madeInService.getById(id);
        return Result.content(reponse);
    }
}
