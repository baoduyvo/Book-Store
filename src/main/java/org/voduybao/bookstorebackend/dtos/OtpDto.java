package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class OtpDto {
    @Getter
    public static class Request {
        @NotNull(message = "Vui Lòng Nhập Số Điện Thoại Hoặc Email")
        private String phoneOrEmail;

        public boolean isPhone() {
            return phoneOrEmail.matches("^(\\+?[0-9]{1,4})?([0-9]{10})$");
        }

        public boolean isEmail() {
            return phoneOrEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }
    }
}
