package com.mcy.layui.custom;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class TablePageable {
    private Integer limit; //分页
    private String sort;   //排序字段
    private String order;  //顺序，逆序
    private Integer page; //分页

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public PageRequest bulidPageRequest() {
        int page = (this.page != null) ? this.page - 1 : 0;
        int size = limit != null ? limit : 10;
        if (sort == null) {
            return PageRequest.of(page, size);
        } else {
            Order order2 = new Order(Direction.fromString(order), sort);
            Sort sort2 = Sort.by(order2);
            return PageRequest.of(page, size, sort2);
        }

    }

    public PageRequest bulidPageable(Sort sort) {
        int page = (this.page != null) ? this.page - 1 : 0;
        int size = limit != null ? limit : 10;
        return PageRequest.of(page, size, sort);
    }

    public Sort bulidSort() {
        Order order2 = new Order(Direction.fromString(order), sort);
        Sort sort2 = Sort.by(order2);
        return sort2;
    }
}
