package com.yc.user.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.core.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {
}
