package com.yc.user.api.service;

import com.yc.user.api.dto.UserRoleDTO;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:25 2021/2/23
 */
public interface UserRoleService {
    UserRoleDTO selectOne(UserRoleDTO userRoleDTO);
}
