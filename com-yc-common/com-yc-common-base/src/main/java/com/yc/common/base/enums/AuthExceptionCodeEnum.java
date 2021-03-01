package com.yc.common.base.enums;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 13:52 2021/2/23
 */
@Getter
public enum AuthExceptionCodeEnum {
    //异常码定义
    TOKEN_VALID("TOKEN_VALID(","无效的TOKEN"),
    TOKEN_NO_EXIST("TOKEN_NO_EXIST","TOKEN不存在"),
    AUTH_PARAM_NULL("AUTH_PARAM_NULL","授权参数为空"),

    TOKEN_NULL("TOKEN_NULL","token为空"),
    METHOD_NULL("METHOD_NULL","method为空"),
    MENU_ID_NULL("TOKEN_NULL","menuId为空"),
    URL_NULL("URL_NULL","url为空"),

    URL_AUTH_FAIL("URL_AUTH_FAIL","url授权失败")
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
