package com.yc.user.core.converter;

import com.yc.user.core.entity.UserRoleEntity;
import com.yc.user.facade.dto.UserRoleDTO;
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
