package org.voduybao.bookstorebackend.controllers.v2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(
            summary = "Kiểm tra phản hồi API",
            description = "API kiểm tra xem server có hoạt động hay không",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Thành công",
                            content = @Content(schema = @Schema(implementation = PingResponse.class))
                    )
            }
    )
    public ResponseEntity<PingResponse> ping() {
        PingResponse response = new PingResponse("pong");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Schema(description = "Phản hồi từ API ping")
    public static class PingResponse {
        @Schema(description = "Nội dung phản hồi", example = "pong")
        public String message;

        public PingResponse(String message) {
            this.message = message;
        }
    }
}