package com.yc.common.base.exception;


import com.yc.common.base.enums.AuthExceptionCodeEnum;
import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
@Getter
public class AuthException extends RuntimeException {
    private final AuthExceptionCodeEnum authExceptionCodeEnum;
    public AuthException(AuthExceptionCodeEnum authExceptionCodeEnum) {
        super(authExceptionCodeEnum.getMsg());
        this.authExceptionCodeEnum = authExceptionCodeEnum;
    }
}
