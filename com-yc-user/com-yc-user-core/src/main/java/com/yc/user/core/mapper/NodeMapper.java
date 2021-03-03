package com.yc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.user.core.entity.NodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限节点表 Mapper 接口
 * </p>
 *
 * @author SanGang
 * @since 2020-11-01
 */
@Mapper
public interface NodeMapper extends BaseMapper<NodeEntity> {
    List<String> getAllNode();
}
