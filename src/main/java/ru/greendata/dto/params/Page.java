package ru.greendata.dto.params;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private List<T> list;
    private Long totalCount;

    public Page(List<T> list, Long totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
