package com.yc.user.core.dao;

import com.yc.user.core.entity.UserNodeEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:48 2021/2/23
 */
@Mapper
public interface UserNodeDao {
    List<UserNodeEntity> selectUserNode(UserNodeEntity userNodeEntity);
}
