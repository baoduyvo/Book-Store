package org.voduybao.bookstorebackend.services.merchandise;

import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;

public interface CategorySyncService {
    void syncSaveAll();

    void syncdeleteAll();

    void syncSaveById(Integer id);

    PaginationResult<Category> listAndSearch(String keyword, int page, int size);
}
