package com.yc.user.core.mapper;

import com.yc.user.core.entity.UserNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户节点表Mapper接口
 * @Date: Created in 15:48 2021/2/23
 */
@Mapper
public interface UserNodeMapper {
    List<UserNode> selectUserNode(UserNode userNodeEntity);
}
