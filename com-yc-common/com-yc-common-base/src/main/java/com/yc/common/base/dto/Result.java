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
public class Result<T> implements Serializable {
    private long code;
    private String msg;
    private T data;
    private long time;

    protected Result() {
    }

    public Result(long code, String msg, T data,long time) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = time;
    }
}
