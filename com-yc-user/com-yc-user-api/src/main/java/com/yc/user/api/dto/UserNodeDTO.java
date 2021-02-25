package com.yc.user.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description:  用户节点DTO
 * @Date: Created in 15:46 2021/2/23
 */
@Getter
@Setter
public class UserNodeDTO implements Serializable {
    private Long userId;
    //0-企业 1-前端 2-代理商 3-有传 4-U客后台 5-U客App
    private Integer platform;
    //企业id
    private Long organizeId;
    //菜单id
    private Long menuId;
    //0 get/1 post/2 put/3 delete
    private String methodType;
    //路由地址
    private String uri;
}
