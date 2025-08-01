package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OtpDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "Vui Lòng Nhập Số Điện Thoại Hoặc Email")
        private String phoneOrEmail;
        private String otp;

        public boolean isPhone() {
            return phoneOrEmail.matches("^(\\+?[0-9]{1,4})?([0-9]{10})$");
        }

        public boolean isEmail() {
            return phoneOrEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }
    }

}
