package com.yc.common.base.exception;

import com.yc.common.base.enums.MallExceptionCodeEnum;
import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:02 2021/2/22
 */
@Getter
public class MallException extends RuntimeException {
    private final MallExceptionCodeEnum mallExceptionCodeEnum;
    public MallException(MallExceptionCodeEnum mallExceptionCodeEnum) {
        super(mallExceptionCodeEnum.getMsg());
        this.mallExceptionCodeEnum = mallExceptionCodeEnum;
    }
}
