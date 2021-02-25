package com.yc.user.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description: 权限节点表
 * @Date: Created in 15:56 2021/2/7
 */
@Getter
@Setter
@TableName("node")
public class NodeEntity implements Serializable {
    //权限节点id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //服务端类型(0:Python,1:java,2:Go)
    private Integer type;

    //0 get/1 post/2 put/3 delete
    private String methodType;

    //路由地址
    private String uri;

    //事件
    private String action;

    //描述
    private String description;

    //0启用/1停用
    private Integer status;

    //更新时间
    private Integer updatedAt;

    //创建时间
    private Integer createdAt;
}
