package com.yc.user.facade;

import com.yc.user.facade.dto.UserAuthInfoDTO;
import com.yc.user.facade.exception.UserException;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户授权服务
 * @Date: Created in 15:40 2021/2/23
 */
public interface UserAuthFacade {
    UserAuthInfoDTO getUserAuthority(UserAuthInfoDTO userAuthInfoDTO) throws UserException;
}
