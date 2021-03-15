package com.yc.mall.facade.exception;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
public class MallException extends RuntimeException {
    @Getter
    public enum MallExceptionCodeEnum{
        //异常码定义
        MALL_SHOW_NO_NULL("MALL_SHOW_NO_NULL","mallShopNo为空")
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
    public MallException(MallExceptionCodeEnum mallExceptionCodeEnum) {
        super(mallExceptionCodeEnum.getCode()+"-"+mallExceptionCodeEnum.getMsg());
    }
}
