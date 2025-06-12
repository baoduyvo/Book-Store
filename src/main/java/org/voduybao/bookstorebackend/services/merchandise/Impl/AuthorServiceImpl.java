package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Author;
import org.voduybao.bookstorebackend.dao.entities.merchandise.MadeIn;
import org.voduybao.bookstorebackend.dao.repositories.media.MediaGalleryRepository;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.AuthorRepository;
import org.voduybao.bookstorebackend.dtos.AuthorDto;
import org.voduybao.bookstorebackend.dtos.MadeInDto;
import org.voduybao.bookstorebackend.services.merchandise.AuthorService;
import org.voduybao.bookstorebackend.services.shared.MinioService;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

@Component
@Slf4j(topic = "AUTHOR-IN-SERVICE")
public class AuthorServiceImpl implements AuthorService {

    @Setter(onMethod_ = @Autowired)
    private AuthorRepository authorRepository;
    @Setter(onMethod_ = @Autowired)
    private MediaGalleryRepository mediaGalleryRepository;

    @Setter(onMethod_ = @Autowired)
    private MinioService minioService;

    @Override
    public void create(AuthorDto.CreatorRequest request, MultipartFile image) {
        log.info("Author created ...!");
        Author author = Author.builder()
                .name(request.getName())
                .bio(request.getBio())
                .archived(false)
                .build();
        authorRepository.save(author);

        try {
//            var reponse = minioService.uploadFile(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Integer id, MadeInDto.Request request) {

    }

    @Override
    public PaginationResult<MadeIn> listAndSearch(String keyword, Integer page, Integer size) {
        return null;
    }

    @Override
    public MadeIn getById(Integer id) {
        return null;
    }
}
