package com.yc.common.base.enums;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:30 2021/2/26
 */
@Getter
public enum MallExceptionCodeEnum {
    //异常码定义
    ML001("ML001","无效的用户")
    ;
    private final String code;
    private final String msg;
    MallExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static MallExceptionCodeEnum getEnum(int code) {
        for (MallExceptionCodeEnum ele : MallExceptionCodeEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
