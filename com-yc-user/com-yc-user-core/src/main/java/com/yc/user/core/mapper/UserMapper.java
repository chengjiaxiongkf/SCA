package com.yc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.core.entity.UserEntity;
import com.yc.user.facade.dto.UserAuthInfoDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户表Mapper接口
 * @Date: Created in 14:55 2021/3/4
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    int updateHeadimgById(UserEntity user);
    int updateNicknameById(UserEntity user);
    int updateSexById(UserEntity user);
    UserAuthInfoDTO getUserAuthority(long userId);
}
