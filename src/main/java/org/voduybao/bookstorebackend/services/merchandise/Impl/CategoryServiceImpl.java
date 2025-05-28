package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.merchandise.Category;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.CategoryRepository;
import org.voduybao.bookstorebackend.dtos.CategoryDto;
import org.voduybao.bookstorebackend.services.merchandise.CategoryServeice;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationResult;
import org.voduybao.bookstorebackend.tools.response.panigation.PaginationUtils;

import java.util.List;
import java.util.Objects;

import static org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors.CATEGORY_NAME_DUPLICATE;
import static org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors.CATEGORY_NAME_SPECIAL_CHAR;

@Component
@Slf4j(topic = "CATEGORY-SERVICE")
public class CategoryServiceImpl implements CategoryServeice {

    @Setter(onMethod_ = @Autowired)
    private CategoryRepository categoryRepository;

    private static final String SPECIAL_CHAR = "[\\p{L}\\p{N}\\s]+";
    private final static List<Integer> DEFAULT_PARENT_CATE = List.of(1, 2, 3, 4, 5);

    @Override
    public PaginationResult<Category> list(int page, int size) {
        log.info("Category Get List Categories With Paginations ...!");
        PaginationUtils.PaginationResult pagination = PaginationUtils.validateAndConvert(page, size);

        List<Category> categories = categoryRepository.findFirstByParentIdIsNotNull(pagination.pageSize(), pagination.offset());

        int total = Math.toIntExact(categoryRepository.count());

        return new PaginationResult<>(total, categories, page, size);
    }

    @Override
    public void create(CategoryDto.CreatorRequest request) {
        log.info("Categories created ...!");
        if (validateDuplicateName(request.getName(), null))
            throw new ResponseException(CATEGORY_NAME_DUPLICATE);

        Category categoryParent = null;
        if (request.getParentId() != null) {
            categoryParent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .archived(false)
                .parent(categoryParent)
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void update(Integer id, CategoryDto.UpdateParentRequest updateParentDto) {
        log.info("Categories updated ...!");
        validateRoot(id);
        validateAndUpdate(id, updateParentDto.getName());
        validateAndUpdate(updateParentDto.getDescription(), id);
        validateAndUpdate(id, updateParentDto.getParentId());
    }

    @Override
    public void deleteChild(Integer id) {
        log.info("Categories delete child ...!");
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));

        if (DEFAULT_PARENT_CATE.contains(id))
            throw new ResponseException(ResponseErrors.CATEGORY_ROOT);

        if (category.getParent() == null)
            throw new ResponseException(ResponseErrors.CATEGORY_ROOT);

        if (!Objects.isNull(categoryRepository.checkExistParentId(id)))
            throw new ResponseException(ResponseErrors.CATEGORY_HAVE_CHILD);

        category.setArchived(true);
        categoryRepository.save(category);
    }

    private void validateRoot(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));
        if (DEFAULT_PARENT_CATE.contains(id))
            throw new ResponseException(ResponseErrors.CATEGORY_ROOT);
        if (category.getParent() == null)
            throw new ResponseException(ResponseErrors.CATEGORY_ROOT);
    }

    private void validateAndUpdate(Integer id, String name) {
        if (Objects.isNull(name)) return;
        if (!name.matches(SPECIAL_CHAR))
            throw new ResponseException(CATEGORY_NAME_SPECIAL_CHAR);
        if (validateDuplicateName(name.trim(), id))
            throw new ResponseException(CATEGORY_NAME_DUPLICATE);
        Category category = categoryRepository.findCategoriesBy(id);
        category.setName(name);
        categoryRepository.save(category);
    }

    private void validateAndUpdate(String description, Integer id) {
        if (Objects.isNull(description)) return;
        Category category = categoryRepository.findCategoriesBy(id);
        category.setDescription(description);
        categoryRepository.save(category);
    }

    private void validateAndUpdate(Integer id, Integer parentId) {
        if (!Objects.isNull(parentId)) {
            if (!Objects.isNull(categoryRepository.checkExistParentId(id)))
                throw new ResponseException(ResponseErrors.CATEGORY_HAVE_CHILD);
            Category categoryChil = categoryRepository.findCategoriesBy(id);
            Category categoryParent = null;
            if (parentId != null) {
                categoryParent = categoryRepository.findById(parentId)
                        .orElseThrow(() -> new ResponseException(ResponseErrors.NOT_FOUND_ID_CATEGORY));
            }
            categoryChil.setParent(categoryParent);
            categoryRepository.save(categoryChil);
        }
    }

    private boolean validateDuplicateName(String name, Integer id) {
        return !categoryRepository.findByName(name, id).isEmpty();
    }
}
