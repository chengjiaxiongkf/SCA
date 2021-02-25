package com.yc.user.core.converter;

import com.yc.user.api.dto.UserDTO;
import com.yc.user.api.dto.UserRoleDTO;
import com.yc.user.core.entity.UserEntity;
import com.yc.user.core.entity.UserRoleEntity;
import org.mapstruct.Mapper;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:28 2021/2/23
 */
@Mapper(componentModel="spring")
public interface UserRoleConverter {
    UserRoleDTO converUserRoleDTO(UserRoleEntity userRoleEntity);
}
