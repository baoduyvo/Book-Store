package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.util.StringUtils;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;
import org.voduybao.bookstorebackend.dao.repositories.searchdocument.CategoryDocRepository;
import org.voduybao.bookstorebackend.dao.entities.searchdocument.CategoryDocument;
import org.voduybao.bookstorebackend.mapper.CategoryMapper;
import org.voduybao.bookstorebackend.services.merchandise.CategorySyncService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = "CATEGORY-SYNC-SERVICE")
public class CategorySyncServiceImpl implements CategorySyncService {

    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;
    @Setter(onMethod_ = @Autowired)
    private CategoryDocRepository categoryDocRepository;

    @Setter(onMethod_ = @Autowired)
    private ElasticsearchTemplate elasticsearchTemplate;
    @Setter(onMethod_ = @Autowired)
    private CategoryMapper mapper;

    public void syncSaveAll() {
        log.info("Category Save Data Synchronization ...!");
        List<Category> categories = categoryRepository.getByParentIdIsNotNullAndArchivedFalse();
        List<CategoryDocument> esDocs = categories.stream()
                .map(this::convertToDocument)
                .collect(Collectors.toList());
        categoryDocRepository.saveAll(esDocs);
    }

    @Override
    public void syncdeleteAll() {
        log.info("Category Deleting Data Synchronization ...!");
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.matchAll(m -> m))
                .build();

        elasticsearchTemplate.delete(query, CategoryDocument.class);
    }

    @Override
    public void syncSaveById(Integer id) {
        log.info("Category Data Synchronization Save By Id ...!");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));
        CategoryDocument document = convertToDocument(category);
        categoryDocRepository.save(document);
    }

    @Override
    public PaginationResult<Category> listAndSearch(String keyword, int page, int size) {
        log.info("Category Get List Categories Data Synchronization With Paginations ...!");
        var pagination = PaginationUtils.validateAndConvert(page, size);
        List<Category> results;
        int totalCount;

        if (!StringUtils.hasText(keyword)) {
            results = categoryRepository.findFirstByParentIdIsNotNull(pagination.pageSize(), pagination.offset());
            totalCount = Math.toIntExact(categoryRepository.count());
        } else {
            var query = NativeQuery.builder()
                    .withQuery(q -> q.bool(b -> b
                            .should(s -> s.match(m -> m.field("name").query(keyword)
                                    .fuzziness("AUTO")
                                    .minimumShouldMatch("70%")
                                    .boost(2.0F)))
                            .should(s -> s.match(m -> m.field("description")
                                    .query(keyword)
                                    .fuzziness("AUTO")
                                    .minimumShouldMatch("70%")))
                    ))
                    .withPageable(PageRequest.of(page - 1, size))
                    .build();

            var searchHits = elasticsearchTemplate.search(query, CategoryDocument.class);
            totalCount = (int) Math.min(searchHits.getTotalHits(), Integer.MAX_VALUE);

            results = searchHits.getSearchHits().stream()
                    .map(hit -> mapper.categoryMapDocumentById(hit.getContent().getId()))
                    .toList();
        }

        return new PaginationResult<>(totalCount, results, page, size);
    }

    private CategoryDocument convertToDocument(Category entity) {
        CategoryDocument doc = new CategoryDocument();
        doc.setId(entity.getId());
        doc.setName(entity.getName());
        doc.setDescription(entity.getDescription());
        return doc;
    }
}
