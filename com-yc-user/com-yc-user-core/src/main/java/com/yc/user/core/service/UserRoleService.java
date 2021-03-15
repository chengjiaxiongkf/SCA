package com.yc.user.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.user.core.converter.UserRoleConverter;
import com.yc.user.core.entity.UserRole;
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
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userRoleDTO.getUserId())
                .eq(UserRole::getOrganizeId,userRoleDTO.getOrganizeId())
                .eq(UserRole::getPlatform,userRoleDTO.getPlatform())
                .eq(UserRole::getStatus,0)
                .eq(UserRole::getRoleId,0);
        return userRoleConverter.converUserRoleDTO(userRoleMapper.selectOne(queryWrapper));
    }
}
