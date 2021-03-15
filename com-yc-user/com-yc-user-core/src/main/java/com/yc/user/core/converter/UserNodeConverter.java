package com.yc.user.core.converter;

import com.yc.user.core.entity.UserNode;
import com.yc.user.facade.dto.UserNodeDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:56 2021/2/23
 */
@Mapper(componentModel="spring")
public interface UserNodeConverter {
    UserNodeDTO converUserNodeDTO(UserNode userNodeEntity);
    UserNode converUserNodeEntity(UserNodeDTO userNodeDTO);
    List<UserNodeDTO> converUserNodeDTOList(List<UserNode> userNodeEntities);
}
