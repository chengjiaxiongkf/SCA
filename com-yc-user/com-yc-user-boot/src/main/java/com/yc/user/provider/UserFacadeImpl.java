package com.yc.user.provider;

import com.yc.common.base.dto.PageResult;
import com.yc.user.core.service.UserService;
import com.yc.user.facade.dto.UserDTO;
import com.yc.user.core.converter.UserConverter;
import com.yc.user.facade.exception.UserException;
import com.yc.user.facade.UserFacade;
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
public class UserFacadeImpl implements UserFacade {
    @Resource
    private UserService userService;
    @Resource
    private UserConverter userConverter;

    @Override
    public boolean insert(UserDTO userDTO) throws UserException {
        return userService.insert(userDTO);
    }

    @Override
    public boolean updateById(UserDTO userDTO) throws UserException {
        return userService.updateById(userDTO);
    }

    @Override
    public boolean updateHeadimgById(UserDTO userDTO) throws UserException {
        return userService.updateHeadimgById(userDTO);
    }

    @Override
    public boolean updateNicknameById(UserDTO userDTO) throws UserException {
        return userService.updateNicknameById(userDTO);
    }

    @Override
    public UserDTO getById(Long id) throws UserException {
        return userService.getById(id);
    }

    @Override
    public PageResult<UserDTO> page(UserDTO userDTO) throws UserException {
        return userService.page(userDTO);
    }

    @Override
    public List<UserDTO> list(UserDTO userDTO) throws UserException {
        return userService.list(userDTO);
    }

    @Override
    public boolean removeById(Long id) throws UserException {
        return userService.removeById(id);
    }

    @Override
    public boolean deleteByIds(String ids) throws UserException {
        return userService.deleteByIds(ids);
    }
}
