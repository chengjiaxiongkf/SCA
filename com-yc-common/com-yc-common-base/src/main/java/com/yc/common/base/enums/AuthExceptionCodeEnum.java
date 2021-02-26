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
    AU001("AU001","无效的TOKEN"),
    AU002("AU002","授权参数为空"),
    AU003("AU003","TOKEN为空"),
    AU004("AU004","TOKEN不存在"),
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
