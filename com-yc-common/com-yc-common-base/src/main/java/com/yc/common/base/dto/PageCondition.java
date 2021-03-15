package com.yc.common.base.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description:  分页查询条件对象
 * @Date: Created in 14:46 2021/3/4
 */
@Getter
@Setter
public class PageCondition implements Serializable {
    private long current;   //当前页
    private long size;  //每页显示数量
}
