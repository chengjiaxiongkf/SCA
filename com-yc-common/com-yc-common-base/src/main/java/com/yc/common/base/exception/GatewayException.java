package com.yc.common.base.exception;

import com.yc.common.base.enums.ExceptionCodeEnum;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:24 2021/2/9
 */
public class GatewayException extends BusinessException {
    public GatewayException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum);
    }
}
