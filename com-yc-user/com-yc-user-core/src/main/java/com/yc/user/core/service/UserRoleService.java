package com.yc.user.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.user.core.converter.UserRoleConverter;
import com.yc.user.core.entity.UserRoleEntity;
import com.yc.user.core.mapper.UserRoleMapper;
import com.yc.user.facade.dto.UserRoleDTO;
import com.yc.user.facade.exception.UserException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
@Service
public class UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserRoleConverter userRoleConverter;

    public UserRoleDTO selectOne(UserRoleDTO userRoleDTO) throws UserException {
        //先判断是否超管
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userRoleDTO.getUserId())
                .eq(UserRoleEntity::getOrganizeId,userRoleDTO.getOrganizeId())
                .eq(UserRoleEntity::getPlatform,userRoleDTO.getPlatform())
                .eq(UserRoleEntity::getStatus,0)
                .eq(UserRoleEntity::getRoleId,0);
        return userRoleConverter.converUserRoleDTO(userRoleMapper.selectOne(queryWrapper));
    }
}
