package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

public class CategoryDto {
    @Getter
    public static class CreatorRequest {
        @NotNull(message = "Name is not null")
        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name must not exceed 255 characters")
        @Pattern(regexp = "[\\p{L}\\p{N}\\s]+", message = "Name must not contain special characters")
        private String name;
        private String description;
        @NotNull(message = "Parent Id must contain")
        private Integer parentId;
    }

    @Data
    public static class UpdateParentRequest{

        private Integer parentId;

        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name must not exceed 255 characters")
        @Pattern(regexp = "[\\p{L}\\p{N}\\s]+", message = "Name must not contain special characters")
        private String name;

        private String description;
    }

    @Getter
    public static class CategoryAssignmentRequest{
        @NotNull(message = "category ids is required")
        private List<Integer> categoryIds;
    }
}
