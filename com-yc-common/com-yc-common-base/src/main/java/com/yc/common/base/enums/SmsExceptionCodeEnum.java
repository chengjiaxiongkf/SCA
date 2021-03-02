package com.yc.common.base.enums;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:12 2021/3/2
 */
@Getter
public enum SmsExceptionCodeEnum {
    //异常码定义
    PHONE_NULL("PHONE_NULL","手机号不能为空"),
    SEND_FAIL("SEND_FAIL","短信发送失败"),
    SEND_REQUEST_FAIL("SEND_REQUEST_FAIL","短信请求失败"),
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
