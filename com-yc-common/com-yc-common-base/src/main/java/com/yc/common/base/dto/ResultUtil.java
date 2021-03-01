package com.yc.common.base.dto;

import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultCodeEnum;

/**
 * @Author chengjiaxiong
 * @Date 2021/2/5 14:05
 */
public class ResultUtil {
    public static boolean isSuccess(Result result){
        return ResultCodeEnum.SUCCESS.getCode().equals(result.getCode());
    }

    /**
     * 成功返回结果
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null,getTime());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data,getTime());
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), message, data,getTime());
    }

    /**
     * 失败500返回结果
     */
    public static <T> Result<T> failed() {
        return failed(ResultCodeEnum.FAILED.getMessage());
    }
    public static <T> Result<T> failed(String message) {
        return new Result<T>(ResultCodeEnum.FAILED.getCode(), message, null,getTime());
    }
    public static <T> Result<T> failed(ResultCodeEnum resultCodeEnum) {
        return new Result<T>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null,getTime());
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed(ResultCodeEnum.VALIDATE_FAILED.getMessage());
    }
    public static <T> Result<T> validateFailed(String message) {
        return new Result<T>(ResultCodeEnum.VALIDATE_FAILED.getCode(), message, null,getTime());
    }

    /**
     * 未登录返回结果
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<T>(ResultCodeEnum.UNAUTHORIZED.getCode(), ResultCodeEnum.UNAUTHORIZED.getMessage(), data,getTime());
    }

    /**
     * 未授权返回结果
     */
    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCodeEnum.FORBIDDEN.getCode(), ResultCodeEnum.FORBIDDEN.getMessage(), data,getTime());
    }

    public static long getTime(){
        return System.currentTimeMillis()/1000L;
    }
}
