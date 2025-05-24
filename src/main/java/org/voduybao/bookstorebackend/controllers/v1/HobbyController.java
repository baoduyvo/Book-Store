package org.voduybao.bookstorebackend.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.voduybao.bookstorebackend.dtos.HobbyDto;
import org.voduybao.bookstorebackend.services.user.HobbyService;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/hobbies")
@RequiredArgsConstructor
public class HobbyController {
    @Setter(onMethod_ = @Autowired)
    private HobbyService hobbyService;

    @GetMapping("")
    public Result list() {
        var reponse = hobbyService.list();
        return Result.content(reponse);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id) {
        var reponse = hobbyService.getById(id);
        return Result.content(reponse);
    }

    @PostMapping("")
    public Result create(@RequestBody @Validated HobbyDto.Request request) {
        hobbyService.create(request);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody @Validated HobbyDto.Request request) {
        hobbyService.update(id, request);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        hobbyService.delete(id);
        return Result.success();
    }

}