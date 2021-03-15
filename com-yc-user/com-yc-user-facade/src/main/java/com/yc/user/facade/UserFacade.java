package com.yc.user.facade;

import com.yc.common.base.dto.PageResult;
import com.yc.user.facade.dto.UserDTO;
import com.yc.user.facade.exception.UserException;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 18:16 2021/2/7
 */
public interface UserFacade {
    boolean insert(UserDTO userDTO) throws UserException;
    boolean updateById(UserDTO userDTO) throws UserException;
    boolean updateHeadimgById(UserDTO userDTO) throws UserException;
    boolean updateNicknameById(UserDTO userDTO) throws UserException;
    UserDTO getById(Long id) throws UserException;
    PageResult<UserDTO> page(UserDTO userDTO) throws UserException;
    List<UserDTO> list(UserDTO userDTO) throws UserException;
    boolean removeById(Long id) throws UserException;
    boolean deleteByIds(String ids) throws UserException;
}
