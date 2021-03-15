package com.yc.user.core.converter;

import com.yc.user.facade.dto.UserAuthInfoDTO;
import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.user.facade.dto.UserRoleDTO;
import org.mapstruct.Mapper;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:42 2021/2/23
 */
@Mapper(componentModel="spring")
public interface UserAuthInfoConverter {
    UserRoleDTO converUserRoleDTO(UserAuthInfoDTO userAuthInfoDTO);
    UserNodeDTO converUserNodeDTO(UserAuthInfoDTO userAuthInfoDTO);
}
