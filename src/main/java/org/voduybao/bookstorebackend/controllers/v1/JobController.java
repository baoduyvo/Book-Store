package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.EducationDto;
import org.voduybao.bookstorebackend.dtos.JobDto;
import org.voduybao.bookstorebackend.services.user.EducationService;
import org.voduybao.bookstorebackend.services.user.JobService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
@Tag(name = "06 - Job Controller", description = "API quản lý các bản ghi công việc của người dùng")
@Hidden
public class JobController {
    @Setter(onMethod_ = @Autowired)
    private JobService jobService;

    @GetMapping("")
    public Result list() {
        var reponse = jobService.list();
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        var reponse = jobService.getById(id);
        return Result.content(reponse);
    }

    @PostMapping("")
    public Result create(@RequestBody @Validated JobDto.Request request) {
        jobService.create(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody @Validated JobDto.Request request) {
        jobService.update(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        jobService.delete(id);
        return Result.success();
    }

}