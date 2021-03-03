package com.yc.user.core.mapper;

import com.yc.user.core.entity.UserNodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:48 2021/2/23
 */
@Mapper
public interface UserNodeMapper {
    List<UserNodeEntity> selectUserNode(UserNodeEntity userNodeEntity);
}
