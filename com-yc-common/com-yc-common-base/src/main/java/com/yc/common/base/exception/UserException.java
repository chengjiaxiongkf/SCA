package com.yc.common.base.exception;

import com.yc.common.base.enums.ExceptionCodeEnum;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:05 2021/2/8
 */
public class UserException extends BusinessException {
    public UserException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum);
    }
}
