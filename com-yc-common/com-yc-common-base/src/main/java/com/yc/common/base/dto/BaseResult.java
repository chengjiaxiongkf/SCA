package com.yc.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:49 2021/3/4
 */
@Getter
@Setter
public class BaseResult implements Serializable {
    private String code;
    private String msg;
}
