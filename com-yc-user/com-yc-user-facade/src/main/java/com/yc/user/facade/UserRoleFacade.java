package com.yc.user.facade;

import com.yc.user.facade.dto.UserRoleDTO;
import com.yc.user.facade.exception.UserException;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
public interface UserRoleFacade {
    UserRoleDTO selectOne(UserRoleDTO userRoleDTO) throws UserException;
}
