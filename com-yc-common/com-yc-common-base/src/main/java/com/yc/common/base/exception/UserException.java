package com.yc.common.base.exception;

import com.yc.common.base.enums.UserExceptionCodeEnum;
import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:30 2021/2/26
 */
@Getter
public class UserException extends RuntimeException {
    private UserExceptionCodeEnum userExceptionCodeEnum;
    public UserException(UserExceptionCodeEnum authExceptionCodeEnum) {
        super(authExceptionCodeEnum.getMsg());
        this.userExceptionCodeEnum = authExceptionCodeEnum;
    }
}
