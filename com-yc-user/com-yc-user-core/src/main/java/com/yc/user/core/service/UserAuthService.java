package com.yc.user.core.service;

import com.yc.user.core.converter.UserAuthInfoConverter;
import com.yc.user.facade.dto.UserAuthInfoDTO;
import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.user.facade.dto.UserRoleDTO;
import com.yc.user.facade.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:   用户授权信息服务
 * @Date: Created in 15:40 2021/2/23
 */
@Service
public class UserAuthService {
    @Autowired
    private UserNodeService userNodeService;
    @Autowired
    private UserRoleService userRoleService;
    @Resource
    private UserAuthInfoConverter userAuthInfoConverter;

    public UserAuthInfoDTO getUserAuthority(UserAuthInfoDTO userAuthInfoDTO) throws UserException {
        UserRoleDTO userRoleDTO = userAuthInfoConverter.converUserRoleDTO(userAuthInfoDTO);
        UserNodeDTO userNodeDTO = userAuthInfoConverter.converUserNodeDTO(userAuthInfoDTO);
        userAuthInfoDTO.setAdmin(userRoleService.selectOne(userRoleDTO)!=null);  //用户是否是超级管理员
        userAuthInfoDTO.setNodes(userNodeService.selectUserNode(userNodeDTO)); //用户拥有的权限节点
        return userAuthInfoDTO;
    }
}
