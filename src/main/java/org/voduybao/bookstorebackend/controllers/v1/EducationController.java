package org.voduybao.bookstorebackend.controllers.v1;

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
@RequestMapping("/v1/educations")
@RequiredArgsConstructor
public class EducationController {
    @Setter(onMethod_ = @Autowired)
    private EducationService educationService;

    @GetMapping("")
    public Result list() {
        var reponse = educationService.list();
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        var reponse = educationService.getById(id);
        return Result.content(reponse);
    }

    @PostMapping("")
    public Result create(@RequestBody @Validated EducationDto.Request request) {
        educationService.create(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody @Validated EducationDto.Request request) {
        educationService.update(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        educationService.delete(id);
        return Result.success();
    }

}