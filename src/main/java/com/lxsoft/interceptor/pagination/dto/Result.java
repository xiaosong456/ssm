package com.lxsoft.interceptor.pagination.dto;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Collection;

/**
 * Result
 *
 * @author lxs
 * @version v1.0.0
 */
public class Result {

    private Collection rows;
    private int total;

    public Collection getRows() {
        return rows;
    }

    public void setRows(Collection rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
