package com.yc.user.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:  用户授权信息封装
 * @Date: Created in 11:31 2021/2/18
 */
@Getter
@Setter
public class UserAuthInfoDTO implements Serializable {
    private Long userId;
    private Long menuId;
    private String shopNo;
    private Integer platform;
    private Integer organizeId;
    private List<UserNodeDTO> nodes; //可以请求的权限节点
    private boolean isAdmin; //是否是超管
}
