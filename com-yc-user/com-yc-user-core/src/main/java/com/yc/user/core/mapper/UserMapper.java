package com.yc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.core.entity.User;
import com.yc.user.facade.dto.UserAuthInfoDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户表Mapper接口
 * @Date: Created in 14:55 2021/3/4
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    int updateHeadimgById(User user);
    int updateNicknameById(User user);
    int updateSexById(User user);
    UserAuthInfoDTO getUserAuthority(long userId);
}
