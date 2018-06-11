package com.lxsoft.common.exception;

/**
 * SysException
 * 公共异常类
 * @author lxs
 * @version v1.0.0
 */
public class SysException extends RuntimeException {

    private Integer code;

    public SysException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }

    public SysException(Integer code,String message){
        super(message);
        this.code=code;
    }


}
