package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ProductBundleDto {
    @Getter
    public static class ProductBundleRequest {
        @NotNull(message = "Name is not null")
        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name must not exceed 255 characters")
        @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Name must not contain special characters")
        private String name;
        @NotNull(message = "description is required")
        private String description;
        @NotNull(message = "price is required")
        private Integer price;
        private Integer discountPercent;

    }

    @Getter
    public static class ProductBundleIsActiveRequest {
        @NotNull(message = "is_active is required")
        private Boolean isActive;
    }

    @Getter
    public static class ReleaseDateRequest {
        @NotNull(message = "release_date is required")
        private String releaseDate;
    }
}
