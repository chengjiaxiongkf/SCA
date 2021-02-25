package com.yc.authentication.api.exception;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 13:52 2021/2/23
 */
@Getter
public enum AuthExceptionCodeEnum {
    //异常码定义
    TOKEN_INVALID("AU001","无效的TOKEN"),
    AUTHENTICATION_PARAM_IS_NULL("AU002","授权参数为空"),
    TOKEN_IS_NULL("AU003","TOKEN为空"),
    TOKEN_IS_EXIST("AU004","TOKEN不存在"),
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
