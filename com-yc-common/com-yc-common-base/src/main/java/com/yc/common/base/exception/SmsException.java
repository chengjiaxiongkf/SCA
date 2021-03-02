package com.yc.common.base.exception;

import com.yc.common.base.enums.MallExceptionCodeEnum;
import com.yc.common.base.enums.SmsExceptionCodeEnum;
import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:12 2021/3/2
 */
@Getter
public class SmsException extends RuntimeException {
    private final SmsExceptionCodeEnum smsExceptionCodeEnum;
    public SmsException(SmsExceptionCodeEnum smsExceptionCodeEnum) {
        super(smsExceptionCodeEnum.getMsg());
        this.smsExceptionCodeEnum = smsExceptionCodeEnum;
    }
}
