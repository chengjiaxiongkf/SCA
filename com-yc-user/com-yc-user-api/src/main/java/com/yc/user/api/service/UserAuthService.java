package com.yc.user.api.service;

import com.yc.user.api.dto.UserAuthInfoDTO;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户授权服务
 * @Date: Created in 15:40 2021/2/23
 */
public interface UserAuthService {
    UserAuthInfoDTO getUserAuthority(UserAuthInfoDTO userAuthInfoDTO);
}
