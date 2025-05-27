package org.voduybao.bookstorebackend.controllers.v1;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voduybao.bookstorebackend.services.merchandise.CategoryServeice;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Setter(onMethod_ = @Autowired)
    private CategoryServeice categoryServeice;

    @GetMapping("")
    public Result list() {
        return Result.success();
    }
}
