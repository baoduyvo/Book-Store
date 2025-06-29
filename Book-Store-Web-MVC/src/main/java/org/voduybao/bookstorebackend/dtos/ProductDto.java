package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    @Getter
    @Setter
    public static class CreatorProductRequest {
        @NotBlank(message = "Title not empty")
        @NotNull(message = "Title is required")
        String title;

        @NotNull(message = "description is required")
        String description;

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price;

        @NotNull(message = "cover_image_id is required")
        Long coverImageId;

        List<Integer> authorIds;

        String publicationDate;

        List<Integer> categoryIds;

        List<Long> mediaIds;

        Long mindMapMediaId;

        @Valid
        List<OptionDto.ProductOptionGroup> productOptionGroups;
    }
}
