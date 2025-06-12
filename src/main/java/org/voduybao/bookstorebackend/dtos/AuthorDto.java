package org.voduybao.bookstorebackend.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;


public class AuthorDto {
    @Getter
    @Setter
    public static class CreatorRequest {
        @NotNull(message = "Name is not null")
        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name must not exceed 255 characters")
        @Pattern(regexp = "[\\p{L}\\p{N}\\s]+", message = "Name must not contain special characters")
        String name;

        String bio;
    }

    @Data
    public static class ModifierRequest {
        private String bio;

        @NotEmpty(message = "Name is required")
        @Size(max = 255, message = "Name must not exceed 255 characters")
        @Pattern(regexp = "[\\p{L}\\p{N}\\s]+", message = "Name must not contain special characters")
        private String name;

        Long profileImageId;
    }

    @Getter
    public static class AuthorAssignmentRequest{
        @NotNull
        private List<Integer> authors;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorResponse {
        private Integer id;
        private String name;
        private String bio;
        private Integer totalView;
        private Instant createdAt;
        private Instant updatedAt;
        private MediaDto media;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaDto {
        private Long id;
        private String fileUrl;
        private String fileType;
        private String fileName;
        private Double width;
        private Double height;
        private Long size;
    }
}
