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
import org.voduybao.bookstorebackend.dtos.EducationDto;
import org.voduybao.bookstorebackend.services.user.EducationService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/v1/educations")
@Tag(name = "04 - Education Controller", description = "API quản lý các bản ghi học vấn của người dùng")
@Hidden
public class EducationController {
    @Setter(onMethod_ = @Autowired)
    private EducationService educationService;

    @GetMapping("")
    @Operation(summary = "List education", description = "Lấy danh sách tất cả các bản ghi học vấn")
    public Result list() {
        var reponse = educationService.list();
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get education by id", description = "Lấy danh sách bản ghi học vấn theo id")
    public Result getById(@PathVariable int id) {
        var reponse = educationService.getById(id);
        return Result.content(reponse);
    }

    @PostMapping("")
    @Operation(summary = "create education", description = "tạo các bản ghi học vấn")
    public Result create(@RequestBody @Validated EducationDto.Request request) {
        educationService.create(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    @Operation(summary = "put education", description = "sửa các bản ghi học vấn")
    public Result update(@PathVariable("id") Integer id, @RequestBody @Validated EducationDto.Request request) {
        educationService.update(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete education", description = "xóa các bản ghi học vấn")
    public Result delete(@PathVariable("id") Integer id) {
        educationService.delete(id);
        return Result.success();
    }

}