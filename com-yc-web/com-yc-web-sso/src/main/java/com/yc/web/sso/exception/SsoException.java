package com.yc.web.sso.exception;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
public class SsoException extends RuntimeException {
    @Getter
    public enum SsoExceptionCodeEnum{
        //异常码定义
        URL_AUTH_FAIL("URL_AUTH_FAIL","url授权失败"),
        MENU_ID_NULL("MENU_ID_NULL","menu_id为空"),
        METHOD_NULL("METHOD_NULL","method为空"),
        TOKEN_NULL("TOKEN_NULL","token为空"),
        URL_NULL("URL_NULL","url为空"),
        TOKEN_NO_EXIST("TOKEN_NO_EXIST","token不存在"),
        TOKEN_VALID("TOKEN_VALID","无效的token"),

        ;
        private final String code;
        private final String msg;
        SsoExceptionCodeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static SsoExceptionCodeEnum getEnum(int code) {
            for (SsoExceptionCodeEnum ele : SsoExceptionCodeEnum.values()) {
                if (ele.getCode().equals(code)) {
                    return ele;
                }
            }
            return null;
        }
    }
    public SsoException(SsoExceptionCodeEnum ssoExceptionCodeEnum) {
        super(ssoExceptionCodeEnum.getCode()+"-"+ssoExceptionCodeEnum.getMsg());
    }
}
