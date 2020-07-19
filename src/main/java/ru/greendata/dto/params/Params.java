package ru.greendata.dto.params;

import java.util.ArrayList;
import java.util.List;

public class Params {
    FilterParams filterCriteria;

    List<OrderParams> orders;

    public Params(FilterParams filterCriteria, List<OrderParams> orders) {
        this.filterCriteria = filterCriteria;
        this.orders = orders;
    }

    public Params() {

    }

    public FilterParams getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(FilterParams filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public List<OrderParams> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderParams> orders) {
        this.orders = orders;
    }
}
