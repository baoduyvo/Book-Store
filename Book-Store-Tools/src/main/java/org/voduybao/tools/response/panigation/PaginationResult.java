package org.voduybao.tools.response.panigation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResult<T> {
    private int total;
    private List<T> data;
    private Integer page;
    private Integer pageSize;

    public PaginationResult(int total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public PaginationResult(int total, List<T> data, Integer page, Integer pageSize) {
        this.total = total;
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
    }
}
