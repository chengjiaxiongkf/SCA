package com.yc.user.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.user.api.dto.UserDTO;
import com.yc.common.base.exception.UserException;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 18:16 2021/2/7
 */
public interface UserService {
    boolean insert(UserDTO userDTO) throws UserException;
    boolean updateById(UserDTO userDTO) throws UserException;
    boolean updateHeadimgById(UserDTO userDTO) throws UserException;
    boolean updateNicknameById(UserDTO userDTO) throws UserException;
    UserDTO getById(Long id) throws UserException;
    Page<UserDTO> page(Page page, UserDTO userDTO) throws UserException;
    List<UserDTO> list(UserDTO userDTO) throws UserException;
    boolean removeById(Long id) throws UserException;
    boolean deleteByIds(String ids) throws UserException;
}
