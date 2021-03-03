package com.yc.user.core.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.config.datasource.aspect.DynamicDataSource;
import com.yc.common.util.QueryUtil;
import com.yc.common.base.exception.UserException;
import com.yc.user.api.service.UserService;
import com.yc.user.api.dto.UserDTO;
import com.yc.user.core.converter.UserConverter;
import com.yc.user.core.dao.UserDao;
import com.yc.user.core.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 15:42 2021/2/18
 */
@DubboService
@Slf4j
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserConverter userConverter;

    @Override
    public boolean insert(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.insert(userEntity)>0;
    }

    @Override
    public boolean updateById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateById(userEntity)>0;
    }

    @Override
    public boolean updateHeadimgById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateHeadimgById(userEntity)>0;
    }

    @Override
    public boolean updateNicknameById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateNicknameById(userEntity)>0;
    }

    @Override
    @DynamicDataSource
    public UserDTO getById(Long id) throws UserException {
        UserEntity userEntity = userDao.selectById(id);
        log.info("userVO:"+JSONObject.toJSONString(userEntity));
        return userConverter.converUserDTO(userEntity);
    }

    @Override
    public Page<UserDTO> page(Page page, UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        Page<UserEntity> userPage = (Page) userDao.selectPage(page, QueryUtil.buildWrapper(userEntity, false));
        log.info("asd:"+JSONObject.toJSONString(userPage));
        return userConverter.converUserDTOPage(userPage);
    }

    @Override
    public List<UserDTO> list(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        List<UserEntity> userEntityList = userDao.selectList(QueryUtil.buildWrapper(userEntity, false));
        return userConverter.converUserDTOList(userEntityList);
    }

    @Override
    public boolean removeById(Long id) throws UserException {
        return userDao.deleteById(id)>0;
    }

    @Override
    public boolean deleteByIds(String ids) throws UserException {
        return userDao.deleteBatchIds(CollUtil.toList(ids))>0;
    }
}
