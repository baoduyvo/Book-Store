package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class OptionDto {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ProductOptionRequest {
        @NotBlank(message = "Detail không được để trống")
        @Size(max = 255, message = "Detail không được vượt quá 255 ký tự")
        String detail;
        String description;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ProductOptionGroup {
        @NotBlank(message = "Detail không được để trống")
        @Size(max = 255, message = "Detail không được vượt quá 255 ký tự")
        String title;
        String description;
        @NotEmpty
        @Valid
        List<ProductOptionRequest> productOptions;
    }

    @Getter
    public static class ProductOptionModify {
        Integer id;
        @NotBlank(message = "Detail không được để trống")
        @Size(max = 255, message = "Detail không được vượt quá 255 ký tự")
        String detail;
        String description;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ProductOptionGroupModify {
        Integer id;
        @NotBlank(message = "Detail không được để trống")
        @Size(max = 255, message = "Detail không được vượt quá 255 ký tự")
        String title;
        String description;
        @NotEmpty
        @Valid
        List<ProductOptionModify> productOptionModifies;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProductOptionGroupResponse{
        Integer id;
        String title;
        String description;
        List<ProductOptionResponse> productOptionModifies;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProductOptionResponse {
        Integer id;
        String detail;
        String description;
    }
}
