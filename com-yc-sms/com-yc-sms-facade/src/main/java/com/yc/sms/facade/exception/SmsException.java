package com.yc.sms.facade.exception;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:12 2021/3/2
 */
public class SmsException extends RuntimeException {
    @Getter
    public enum SmsExceptionCodeEnum{
        //异常码定义
        SEND_FAIL("SEND_FAIL","短信发送失败"),
        PHONE_NULL("PHONE_NULL","手机号为空"),
        TEMPLATE_VALID("TEMPLATE_VALID","无效的模板"),
        ;
        private final String code;
        private final String msg;
        SmsExceptionCodeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static SmsExceptionCodeEnum getEnum(int code) {
            for (SmsExceptionCodeEnum ele : SmsExceptionCodeEnum.values()) {
                if (ele.getCode().equals(code)) {
                    return ele;
                }
            }
            return null;
        }
    }
    public SmsException(SmsExceptionCodeEnum smsExceptionCodeEnum) {
        super(smsExceptionCodeEnum.getCode() +"-"+ smsExceptionCodeEnum.getMsg());
    }
}
