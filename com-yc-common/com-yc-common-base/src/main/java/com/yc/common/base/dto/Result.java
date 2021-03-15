package com.yc.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author chengjiaxiong
 * @Date 2021/2/5 14:04
 */
@Getter
@Setter
public class Result<T> extends BaseResult {
    private T data;
    private long time;

    protected Result() {
    }

    public Result(String code, String msg, T data,long time) {
        super.setCode(code);
        super.setMsg(msg);
        this.data = data;
        this.time = time;
    }
}
