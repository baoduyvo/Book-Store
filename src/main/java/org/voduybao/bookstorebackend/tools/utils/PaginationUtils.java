package org.voduybao.bookstorebackend.tools.utils;

import org.voduybao.bookstorebackend.tools.contants.Constant;

public class PaginationUtils {

    public static PaginationResult validateAndConvert(int page, int pageSize) {
        if (page < 1) {
            page = 1;
        }

        if (pageSize < 1 || pageSize > Constant.MAX_PAGE_SIZE) {
            pageSize = Constant.MAX_PAGE_SIZE;
        }

        int offset = (page - 1) * pageSize;

        return new PaginationResult(page, pageSize, offset);
    }

    public static int calculateTotalPages(long totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public record PaginationResult(int page, int pageSize, int offset) { }
}
