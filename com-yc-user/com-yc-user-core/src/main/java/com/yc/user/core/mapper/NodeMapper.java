package com.yc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.core.entity.NodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 权限节点表Mapper接口
 * @Date: Created in 14:55 2021/3/4
 */
@Mapper
public interface NodeMapper extends BaseMapper<NodeEntity> {
    List<String> getAllNode();
}
