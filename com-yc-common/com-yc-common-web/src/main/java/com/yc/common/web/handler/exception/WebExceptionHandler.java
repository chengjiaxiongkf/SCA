package com.yc.common.web.handler.exception;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局运行时异常处理
 */
@RestControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result error(RuntimeException e) {
        e.printStackTrace();
        return ResultUtil.failed(e.getMessage());
    }
}