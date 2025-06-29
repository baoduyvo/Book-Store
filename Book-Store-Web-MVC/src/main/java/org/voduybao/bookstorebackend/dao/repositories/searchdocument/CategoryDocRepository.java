package org.voduybao.bookstorebackend.dao.repositories.searchdocument;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.voduybao.bookstorebackend.dao.entities.searchdocument.CategoryDocument;

import java.util.List;

public interface CategoryDocRepository extends ElasticsearchRepository<CategoryDocument, String> {
    List<CategoryDocument> findByNameContainingIgnoreCase(String name);
}
