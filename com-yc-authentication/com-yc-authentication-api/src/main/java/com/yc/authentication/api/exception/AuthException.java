package com.yc.authentication.api.exception;


/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
public class AuthException extends RuntimeException {
    private String code;
    public AuthException(AuthExceptionCodeEnum authExceptionCodeEnum) {
        super(authExceptionCodeEnum.getMsg());
        this.code = authExceptionCodeEnum.getCode();
    }
}
