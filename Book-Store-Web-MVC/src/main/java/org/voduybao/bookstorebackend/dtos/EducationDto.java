package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class EducationDto {
    @Getter
    public static class Request {
        @Size(max = 255)
        @NotNull(message = "Vui Lòng Nhập Title")
        String title;

        public Request() {}

        public Request(String title) {
            this.title = title;
        }
    }
}
