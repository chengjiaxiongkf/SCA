package com.yc.user.core.service;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.base.dto.PageResult;
import com.yc.common.util.QueryUtil;
import com.yc.user.core.converter.UserConverter;
import com.yc.user.core.entity.UserEntity;
import com.yc.user.core.mapper.UserMapper;
import com.yc.user.facade.dto.UserDTO;
import com.yc.user.facade.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 15:42 2021/2/18
 */

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConverter userConverter;

    public boolean insert(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userMapper.insert(userEntity)>0;
    }

    public boolean updateById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userMapper.updateById(userEntity)>0;
    }
    
    public boolean updateHeadimgById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userMapper.updateHeadimgById(userEntity)>0;
    }
    
    public boolean updateNicknameById(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        return userMapper.updateNicknameById(userEntity)>0;
    }

    public UserDTO getById(Long id) throws UserException {
        UserEntity userEntity = userMapper.selectById(id);
        log.info("userVO:"+ JSONObject.toJSONString(userEntity));
        return userConverter.converUserDTO(userEntity);
    }
    
    public PageResult<UserDTO> page(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        Page page = new Page();
        page.setCurrent(userDTO.getCurrent());
        page.setSize(userDTO.getSize());
        Page<UserEntity> userPage = (Page) userMapper.selectPage(page, QueryUtil.buildWrapper(userEntity, false));
        return userConverter.converUserDTOPage(userPage);
    }
    
    public List<UserDTO> list(UserDTO userDTO) throws UserException {
        UserEntity userEntity = userConverter.converUserEntity(userDTO);
        List<UserEntity> userEntityList = userMapper.selectList(QueryUtil.buildWrapper(userEntity, false));
        return userConverter.converUserDTOList(userEntityList);
    }
    
    public boolean removeById(Long id) throws UserException {
        return userMapper.deleteById(id)>0;
    }
    
    public boolean deleteByIds(String ids) throws UserException {
        return userMapper.deleteBatchIds(CollUtil.toList(ids))>0;
    }
}
