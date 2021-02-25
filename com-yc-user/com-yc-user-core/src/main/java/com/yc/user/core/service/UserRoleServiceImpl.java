package com.yc.user.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.user.api.dto.UserRoleDTO;
import com.yc.user.api.service.UserRoleService;
import com.yc.user.core.converter.UserRoleConverter;
import com.yc.user.core.dao.UserRoleDao;
import com.yc.user.core.entity.UserRoleEntity;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
@DubboService
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserRoleConverter userRoleConverter;

    @Override
    public UserRoleDTO selectOne(UserRoleDTO userRoleDTO) {
        //先判断是否超管
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<UserRoleEntity>()
                .eq(UserRoleEntity::getUserId, userRoleDTO.getUserId())
                .eq(UserRoleEntity::getOrganizeId,userRoleDTO.getOrganizeId())
                .eq(UserRoleEntity::getPlatform,userRoleDTO.getPlatform())
                .eq(UserRoleEntity::getStatus,0)
                .eq(UserRoleEntity::getRoleId,0);
        return userRoleConverter.converUserRoleDTO(userRoleDao.selectOne(queryWrapper));
    }
}
