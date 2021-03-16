package com.yc.organize.facade.exception;

import lombok.Getter;
/**
 * <p>
 * @Description Organize异常处理类
 * </p>
 * @author chengjiaxiong
 * @since 2021-03-16
 * @version 1.0
 */
@Getter
public class OrganizeException extends RuntimeException {
     @Getter
     public enum OrganizeExceptionCodeEnum {
        TEST("1","1"),
        ;
        private final String code;
        private final String msg;
        OrganizeExceptionCodeEnum(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static OrganizeExceptionCodeEnum getEnum(int code) {
            for (OrganizeExceptionCodeEnum ele : OrganizeExceptionCodeEnum.values()) {
                if (ele.getCode().equals(code)) {
                return ele;
                }
            }
            return null;
        }
     }
     public OrganizeException(OrganizeExceptionCodeEnum e) {
        super(e.getCode()+"-"+e.getMsg());
     }
}
