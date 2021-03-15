package com.yc.common.base.enums;

/**
 * @author hkh
 */
public enum AppTypeEnum {
    /**
     * 客户端类型
     */

    MINI_PROGRAM(1, "小程序客户端"),
    WEB(2, "web"),
    IOS(3, "ios客户端"),
    ANDROID(4, "安卓客户端");

    private final int value;

    private final String desc;

    public int value() {
        return value;
    }

    public String desc() {
        return desc;
    }

    AppTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
