package com.yc.user.core.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.base.dto.PageResult;
import com.yc.user.core.entity.UserEntity;
import com.yc.user.facade.dto.UserDTO;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 深拷贝框架，编译时生成getset代码，性能极佳，有业务侵入性
 * @Date: Created in 17:45 2021/2/7
 */
@Mapper(componentModel="spring")
public interface UserConverter {
//    @Mappings({
//            @Mapping(source = "birthday", target = "birth"),
//            @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
//            @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
//            @Mapping(source = "user.age", target = "age"),
//            @Mapping(target = "email", ignore = true)
//    })
    UserEntity converUserEntity(UserDTO userDTO);
    List<UserEntity> converUserEntityList(List<UserDTO> userDTO);
    UserDTO converUserDTO(UserEntity userEntity);
    PageResult<UserDTO> converUserDTOPage(Page<UserEntity> page);
    List<UserDTO> converUserDTOList(List<UserEntity> userDTO);
}
