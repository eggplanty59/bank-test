package ru.greendata.dto.params;

import java.util.ArrayList;
import java.util.List;

public class FilterParams /*implements IFilterParams*/{

    private String operation;
    private List<FilterParams> filterParamsList;
    private List<FilterCriteria> filterCriteriaList;

    public FilterParams(String operation, List<FilterParams> filterParamsList, List<FilterCriteria> filterCriteriaList) {
        this.operation = operation;
        this.filterParamsList = filterParamsList;
        this.filterCriteriaList = filterCriteriaList;
    }

    public FilterParams() {

    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<FilterParams> getFilterParamsList() {
        return filterParamsList;
    }

    public void setFilterParamsList(List<FilterParams> filterParamsList) {
        this.filterParamsList = filterParamsList;
    }

    public List<FilterCriteria> getFilterCriteriaList() {
        return filterCriteriaList;
    }

    public void setFilterCriteriaList(List<FilterCriteria> filterCriteriaList) {
        this.filterCriteriaList = filterCriteriaList;
    }
}
