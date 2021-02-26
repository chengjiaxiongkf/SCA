package com.yc.common.base.dto;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description: 枚举了一些常用API操作码
 * @Date: Created in 11:31 2021/2/8
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    VALIDATE_FAILED("404", "参数检验失败"),
    UNAUTHORIZED("401", "未登录或已过期"),
    FORBIDDEN("403", "没有相关权限"),
    NO_ACCESS("405", "禁止访问");


    private String code;
    private String message;

    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
