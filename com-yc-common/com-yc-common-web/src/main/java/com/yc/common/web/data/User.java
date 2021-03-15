package com.yc.common.web.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户VO
 * @Date: Created in 15:56 2021/2/7
 */
@Getter
@Setter
public class User implements Serializable {
    private long userId;//用户Id
    private String shopNo;//商城商铺
    private int platform;//客户端
    private int organizeId;//组织
    private boolean isAdmin; //是否是超管
    private String dataSource;//数据源
}
