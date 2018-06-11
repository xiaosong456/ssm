package com.lxsoft.common.exception;

/**
 * Description: 异常接口
 * Author: lxs
 * Date: 2018/5/8
 * Time: 10:11
 * Version: v1.0.0
 */
public enum ResultEnum {
    PRODUCTGROUP_NOT_EXIST(1,"组合不存在"),
    ;

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultEnum(Integer code, String mesage){
        this.code=code;
        this.message=mesage;
    }

}
