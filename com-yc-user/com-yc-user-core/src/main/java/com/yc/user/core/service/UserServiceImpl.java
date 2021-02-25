package com.yc.user.core.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.util.QueryUtil;
import com.yc.user.api.service.UserService;
import com.yc.user.api.dto.UserDTO;
import com.yc.user.core.converter.UserConverter;
import com.yc.user.core.dao.UserDao;
import com.yc.user.core.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 15:42 2021/2/18
 */
@DubboService
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserConverter userConverter;

    @Override
    public boolean insert(UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.insert(userEntity)>0;
    }

    @Override
    public boolean updateById(UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateById(userEntity)>0;
    }

    @Override
    public boolean updateHeadimgById(UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateHeadimgById(userEntity)>0;
    }

    @Override
    public boolean updateNicknameById(UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userDao.updateNicknameById(userEntity)>0;
    }

    @Override
    public UserDTO getById(Long id) {
        UserEntity userEntity = userDao.selectById(id);
        log.info("userVO:"+JSONObject.toJSONString(userEntity));
        return userConverter.converUserDTO(userEntity);
    }

    @Override
    public Page<UserDTO> page(Page page, UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        Page<UserEntity> userPage = (Page) userDao.selectPage(page, QueryUtil.buildWrapper(userEntity, false));
        log.info("asd:"+JSONObject.toJSONString(userPage));
        return userConverter.converUserDTOPage(userPage);
    }

    @Override
    public List<UserDTO> list(UserDTO userDTO) {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        List<UserEntity> userEntityList = userDao.selectList(QueryUtil.buildWrapper(userEntity, false));
        return userConverter.converUserDTOList(userEntityList);
    }

    @Override
    public boolean removeById(Long id) {
        return userDao.deleteById(id)>0;
    }

    @Override
    public boolean deleteByIds(String ids) {
        return userDao.deleteBatchIds(CollUtil.toList(ids))>0;
    }
}
