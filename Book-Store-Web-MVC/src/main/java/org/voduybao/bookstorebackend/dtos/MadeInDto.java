package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class MadeInDto {
    @Getter
    public static class Request {
        @Size(max = 255)
        @NotNull(message = "Vui Lòng Nhập Name")
        String name;
    }
}
