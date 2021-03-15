package com.yc.common.base.enums;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description: 请求来源枚举定义
 * @Date: Created in 14:37 2021/2/9
 */
@Getter
public enum RequestSourceEnum {
    /**
     * 有传请求来源
     */
    WEB("web","有传-U客"),//有传-web
    MINIPROGRAM("MiniProgram","有传-MiniProgram"),//有传-小程序
    APP("App","有传-App"),//有传-app
    ;

    private final String code;
    private final String name;

    RequestSourceEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

    public static RequestSourceEnum getEnum(String code) {
        for (RequestSourceEnum ele : RequestSourceEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
