package com.yc.common.base.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:    分页结果对象
 * @Date: Created in 14:44 2021/3/4
 */
@Getter
@Setter
public class PageResult<T> implements Serializable {
    private List<T> records;    //记录
    private long total;         //总数量

    public PageResult(){
    }
    public PageResult(List<T> records,long total){
        this.records = records;
        this.total = total;
    }
}
