package org.voduybao.bookstorebackend.controllers.v1;

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
@RequestMapping("/v1/product/authors")
@RequiredArgsConstructor
@Tag(name = "13 - Product Author Controller", description = "API quản lý các sản phẩm có tác giả")
public class AuthorController {

    @Setter(onMethod_ = @Autowired)
    private MadeInService madeInService;

    @AdminRequired
    @PostMapping("")
    @Operation(summary = "create author", description = "tạo các bản ghi về tác giả")
    public Result createBookBundle(@Validated @RequestBody MadeInDto.Request request) {
        madeInService.create(request);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}")
    @Operation(summary = "put author", description = "sửa các bản ghi tác giả")
    public Result update(@PathVariable("id") Integer id,
                         @RequestBody @Validated MadeInDto.Request request) {
        madeInService.update(id, request);
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    @Operation(summary = "delete author", description = "xóa các bản ghi tác giả")
    public Result delete(@PathVariable("id") Integer id) {
        madeInService.delete(id);
        return Result.success();
    }

    @GetMapping("")
    @Operation(summary = "list author and search keyword",
            description = "danh sách bản ghi về tác giả và tìm kiếm theo từ khóa")
    public Result listAndSearch(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        var reponse = madeInService.listAndSearch(keyword, page, size);
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get author by id", description = "lấy tác giả theo id")
    public Result getById(@PathVariable int id) {
        var reponse = madeInService.getById(id);
        return Result.content(reponse);
    }
}
