package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Education Controller", description = "API quản lý các bản ghi học vấn của người dùng")
public class EducationController {
    @Setter(onMethod_ = @Autowired)
    private EducationService educationService;

    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Result.Data.class)))
    })
    @Operation(summary = "List education", description = "Lấy danh sách tất cả các bản ghi học vấn")
    public Result list() {
        var reponse = educationService.list();
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Result.Data.class)))
    })
    @Operation(summary = "get education by id", description = "Lấy danh sách bản ghi học vấn theo id")
    public Result getById(@PathVariable int id) {
        var reponse = educationService.getById(id);
        return Result.content(reponse);
    }

    @PostMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Result.Data.class)))
    })
    @Operation(summary = "create education", description = "tạo các bản ghi học vấn")
    public Result create(@RequestBody @Validated EducationDto.Request request) {
        educationService.create(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Result.Data.class)))
    })
    @Operation(summary = "put education", description = "sửa các bản ghi học vấn")
    public Result update(@PathVariable("id") Integer id, @RequestBody @Validated EducationDto.Request request) {
        educationService.update(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Result.Data.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = Result.Data.class)))
    })
    @Operation(summary = "delete education", description = "xóa các bản ghi học vấn")
    public Result delete(@PathVariable("id") Integer id) {
        educationService.delete(id);
        return Result.success();
    }

}