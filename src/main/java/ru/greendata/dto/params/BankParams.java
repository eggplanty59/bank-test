package ru.greendata.dto.params;

import ru.greendata.dto.filters.FilterCriteria;

import java.io.Serializable;
import java.util.List;

public class BankParams implements Serializable {
    List<FilterCriteria> filterCriteria;

    private String orderBy;
    private String orderDir;

    public BankParams(List<FilterCriteria> filterCriteria, String orderBy, String orderDir) {
        this.filterCriteria = filterCriteria;
        this.orderBy = orderBy;
        this.orderDir = orderDir;
    }

    public BankParams() {
    }

    public List<FilterCriteria> getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(List<FilterCriteria> filterCriteria) {
        this.filterCriteria = filterCriteria;
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
