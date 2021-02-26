package com.yc.user.api.service;

import com.yc.user.api.dto.UserRoleDTO;
import com.yc.common.base.exception.UserException;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
public interface UserRoleService {
    UserRoleDTO selectOne(UserRoleDTO userRoleDTO) throws UserException;
}
