package com.yc.common.base.exception;

import com.yc.common.base.enums.ExceptionCodeEnum;

/**
 * @Author: ChengJiaXiong
 * @Description: 业务异常
 * @Date: Created in 16:06 2021/2/8
 */
public class BusinessException extends RuntimeException {
    private ExceptionCodeEnum exceptionCodeEnum;
    public BusinessException(ExceptionCodeEnum exceptionCodeEnum){
        super(exceptionCodeEnum.getMsg());
        this.exceptionCodeEnum = exceptionCodeEnum;
    }
}
