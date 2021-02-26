package com.yc.common.config.common;

import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.enums.AuthExceptionCodeEnum;
import com.yc.common.base.enums.MallExceptionCodeEnum;
import com.yc.common.base.enums.UserExceptionCodeEnum;
import com.yc.common.base.exception.AuthException;
import com.yc.common.base.exception.MallException;
import com.yc.common.base.exception.UserException;
import com.yc.common.base.dto.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局运行时异常处理
 */
@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result error(RuntimeException e) {
        e.printStackTrace();
        return new Result(ResultCodeEnum.FAILED.getCode(),e.getMessage(),null,ResultUtil.getTime());
    }

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public Result error(AuthException e) {
        e.printStackTrace();
        AuthExceptionCodeEnum exceptionCodeEnum = e.getAuthExceptionCodeEnum();
        return new Result(exceptionCodeEnum.getCode(),exceptionCodeEnum.getMsg(),null,ResultUtil.getTime());
    }

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    public Result error(UserException e) {
        e.printStackTrace();
        UserExceptionCodeEnum exceptionCodeEnum = e.getUserExceptionCodeEnum();
        return new Result(exceptionCodeEnum.getCode(),exceptionCodeEnum.getMsg(),null,ResultUtil.getTime());
    }

    @ExceptionHandler(value = MallException.class)
    @ResponseBody
    public Result error(MallException e) {
        e.printStackTrace();
        MallExceptionCodeEnum exceptionCodeEnum = e.getMallExceptionCodeEnum();
        return new Result(exceptionCodeEnum.getCode(),exceptionCodeEnum.getMsg(),null,ResultUtil.getTime());
    }
}