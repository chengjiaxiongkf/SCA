package com.yc.user.core.service;

import com.yc.user.api.dto.UserAuthInfoDTO;
import com.yc.user.api.dto.UserNodeDTO;
import com.yc.user.api.dto.UserRoleDTO;
import com.yc.user.api.service.UserAuthService;
import com.yc.user.api.service.UserNodeService;
import com.yc.user.api.service.UserRoleService;
import com.yc.user.core.converter.UserAuthInfoConverter;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:   用户授权信息服务
 * @Date: Created in 15:40 2021/2/23
 */
@DubboService
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserNodeService userNodeService;
    @Autowired
    private UserRoleService userRoleService;
    @Resource
    private UserAuthInfoConverter userAuthInfoConverter;

    @Override
    public UserAuthInfoDTO getUserAuthority(UserAuthInfoDTO userAuthInfoDTO) {
        UserRoleDTO userRoleDTO = userAuthInfoConverter.converUserRoleDTO(userAuthInfoDTO);
        UserNodeDTO userNodeDTO = userAuthInfoConverter.converUserNodeDTO(userAuthInfoDTO);
        userAuthInfoDTO.setAdmin(userRoleService.selectOne(userRoleDTO)!=null);  //用户是否是超级管理员
        userAuthInfoDTO.setNodes(userNodeService.selectUserNode(userNodeDTO)); //用户拥有的权限节点
        return userAuthInfoDTO;
    }
}
