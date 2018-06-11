package com.lxsoft.interceptor.pagination.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * BootstrapPage
 * 适用于Bootstrap的分页bean
 * @author lxs
 * @version v1.0.0
 */
public class BootstrapPage {
    /**偏移量--bootstrap*/
    private int offset;
    /**当前第几页--EasyUI*/
    private int page;
    /**每页多少条--EasyUI*/
    private int rows;
    /**每页多少条--Bootstrap*/
    private int limit;
    /**排序字段*/
    private String sort;
    /**排序规则*/
    private String order;

    public int getOffset() {
        if (offset != 0) {
            return offset;
        }
        return (page - 1) * rows;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        this.page = (offset / rows) + 1;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        this.limit = rows;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.rows = limit;
        this.limit = limit;
    }

    public String getSort() {
        return ProductField.getField(sort);
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
