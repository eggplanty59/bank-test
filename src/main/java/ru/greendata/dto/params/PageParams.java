package ru.greendata.dto.params;

import java.io.Serializable;

public class PageParams<T> implements Serializable {
    private int start;
    private int page;
    private T params;

    public PageParams(int start, int page, T params) {
        this.start = start;
        this.page = page;
        this.params = params;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
