package ru.greendata.dto.params;

import java.io.Serializable;

public class OrderParams {
    private String orderBy;
    private String orderDir;

    public OrderParams(String orderBy, String orderDir) {
        this.orderBy = orderBy;
        this.orderDir = orderDir;
    }

    public OrderParams() {
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }
}
