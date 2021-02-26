package com.yc.common.base.enums;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:30 2021/2/26
 */
@Getter
public enum UserExceptionCodeEnum {
    //异常码定义
    US001("US001","无效的用户")
    ;
    private final String code;
    private final String msg;
    UserExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static UserExceptionCodeEnum getEnum(int code) {
        for (UserExceptionCodeEnum ele : UserExceptionCodeEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
