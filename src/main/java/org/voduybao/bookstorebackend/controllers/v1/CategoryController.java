package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.CategoryDto;
import org.voduybao.bookstorebackend.services.merchandise.CategoryService;
import org.voduybao.bookstorebackend.services.merchandise.sync.CategorySyncService;
import org.voduybao.bookstorebackend.tools.contants.a.AdminRequired;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Tag(name = "07 - Category Controller", description = "API quản lý các thể loại")
@Hidden
public class CategoryController {

    @Setter(onMethod_ = @Autowired)
    private CategoryService categoryService;
    @Setter(onMethod_ = @Autowired)
    private CategorySyncService categorySyncService;

    @GetMapping("")
    public Result listAndSearch(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        var reponse = categorySyncService.listAndSearch(keyword, page, size);
        return Result.content(reponse);
    }

    @AdminRequired
    @PostMapping("/sync-all")
    public Result syncSaveAll() {
        categorySyncService.syncSaveAll();
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/sync-all")
    public Result syncDeleteAll() {
        categorySyncService.syncdeleteAll();
        return Result.success();
    }

    @AdminRequired
    @PostMapping("")
    public Result create(@Validated @RequestBody CategoryDto.CreatorRequest request) {
        categoryService.create(request);
        return Result.success();
    }

    @AdminRequired
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteChild(id);
        return Result.success();
    }

    @AdminRequired
    @PutMapping("/{id}")
    public Result update(
            @Validated @RequestBody CategoryDto.UpdateParentRequest updateParentDto,
            @PathVariable("id") Integer id
    ) {
        categoryService.update(id, updateParentDto);
        return Result.success();
    }
}
