package com.yc.user.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.api.dto.UserAuthInfoDTO;
import com.yc.user.core.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author SanGang
 * @since 2020-10-13
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<UserEntity> {
    int updateHeadimgById(UserEntity user);
    int updateNicknameById(UserEntity user);
    int updateSexById(UserEntity user);
    UserAuthInfoDTO getUserAuthority(long userId);
}
