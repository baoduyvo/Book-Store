package org.voduybao.bookstorebackend.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.CategoryDto;
import org.voduybao.bookstorebackend.services.merchandise.CategoryService;
import org.voduybao.bookstorebackend.tools.contants.a.AdminRequired;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    private CategoryService categoryService;

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
