package org.voduybao.bookstorebackend.controllers.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/test")
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/ping")
    @Operation(summary = "Kiểm tra phản hồi API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Thành công",
                            content = @Content(schema = @Schema(implementation = Result.Data.class))
                    )
            }
    )
    public Result ping() {
        return Result.content("Pong ✅");
    }
}
