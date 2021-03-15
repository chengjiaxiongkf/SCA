package com.yc.user.provider;

import com.yc.user.core.service.UserRoleService;
import com.yc.user.facade.dto.UserRoleDTO;
import com.yc.user.facade.exception.UserException;
import com.yc.user.facade.UserRoleFacade;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
@DubboService
public class UserRoleProviderImpl implements UserRoleFacade {
    @Resource
    private UserRoleService userRoleService;

    @Override
    public UserRoleDTO selectOne(UserRoleDTO userRoleDTO) throws UserException {
        return userRoleService.selectOne(userRoleDTO);
    }
}
