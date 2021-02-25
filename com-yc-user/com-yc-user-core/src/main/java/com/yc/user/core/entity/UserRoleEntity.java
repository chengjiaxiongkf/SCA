package com.yc.user.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户权限表
 * @Date: Created in 15:57 2021/2/7
 */
@Getter
@Setter
@TableName("user_role")
public class UserRoleEntity implements Serializable {
    //后台用户角色id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    //0-企业 1-前端 2-代理商 3-有传
    private Integer platform;
    //企业id
    private Long organizeId;
    //用户id
    private Long userId;
    //账号类型 0员工/1合伙人
    private Integer userType;
    //角色id
    private Long roleId;
    //角色类型 0企业后台/ 1u客app
    private Integer roleType;
    //app是否超管 0否/1是
    private Integer isBoss;
    //0启用/1停用/2拒绝/3注销/4删除
    private Integer status;
    //更新时间
    private Integer updatedAt;
    //创建时间
    private Integer createdAt;
    //勾选部门集合
    private String viewDepartmentIds;
    //邀请记录id
    private Long inviteRecordId;
}
