package com.yc.auth.api.exception;


import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
public class AuthException extends RuntimeException {
    @Getter
    public enum AuthExceptionCodeEnum {
        //异常码定义
        MENU_ID_NULL("MENU_ID_NULL", "menuId为空"),
        METHOD_NULL("METHOD_NULL", "method为空"),
        TOKEN_NULL("TOKEN_NULL", "token为空"),
        URL_NULL("URL_NULL", "url为空"),
        TOKEN_NO_EXIST("TOKEN_NO_EXIST","token不存在或已过期"),
        TOKEN_VALID("TOKEN_VALID","无效的token"),
        URL_AUTH_FAIL("URL_AUTH_FAIL","url鉴权失败"),
        ;
        private final String code;
        private final String msg;
        AuthExceptionCodeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static AuthExceptionCodeEnum getEnum(int code) {
            for (AuthExceptionCodeEnum ele : AuthExceptionCodeEnum.values()) {
                if (ele.getCode().equals(code)) {
                    return ele;
                }
            }
            return null;
        }
    }
    public AuthException(AuthExceptionCodeEnum authExceptionCodeEnum) {
        super(authExceptionCodeEnum.getCode()+"-"+authExceptionCodeEnum.getMsg());
    }
}
