package com.yc.user.core.dao;

import com.yc.common.config.datasource.aspect.DynamicDataSource;
import com.yc.user.core.entity.UserEntity;
import com.yc.user.core.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:  动态数据源的操作单独事务处理
 * @Date: Created in 15:31 2021/3/3
 */
@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserDaoImpl {
    @Resource
    private UserMapper userMapper;

    @DynamicDataSource
    public UserEntity selectById(Long id){
        return userMapper.selectById(id);
    }
}
