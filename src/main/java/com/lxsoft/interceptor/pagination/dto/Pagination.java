package com.lxsoft.interceptor.pagination.dto;

import com.lxsoft.interceptor.pagination.dto.BootstrapPage;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 适用于easyui 带有查询条件 的分页bean
 *
 * @author zhoujl
 * @create 2016-12-27 15:52
 */
public class Pagination extends BootstrapPage {
    public static final int DEFAULT_PAGE_SIZE = 15;

    private int total;//一共有多少条记录

    private boolean paginationFlg = true;//是否做分页: 该字段默认为true, 为false 不做分页

    private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象

    public Pagination() {
    }

    public Pagination(boolean paginationFlg) {
        this.paginationFlg = paginationFlg;
    }

    public void putParam(String key, Object value) {
        params.put(key, value);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int getRows() {
        if (super.getRows() == 0) {
            return DEFAULT_PAGE_SIZE;
        }

        return super.getRows();
    }

    public boolean isPaginationFlg() {
        return paginationFlg;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
