package org.voduybao.bookstorebackend.controllers.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@RequestMapping("/v1/files")
@Tag(name = "99 - File And Media Gallery Controller", description = "API quản lý các file về media gallery")
@RequiredArgsConstructor
//@AdminRequired
public class MediaGalleryController {




}