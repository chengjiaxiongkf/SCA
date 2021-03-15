package com.yc.user.facade.exception;

import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 20:07 2021/3/3
 */
public class UserException extends RuntimeException {
    @Getter
    public enum UserExceptionCodeEnum {
        //异常码定义
        USER_VALID("USER_VALID","无效的用户")
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

    public UserException(UserExceptionCodeEnum userExceptionCodeEnum){
        super(userExceptionCodeEnum.getCode()+"-"+userExceptionCodeEnum.getMsg());
    }
}
