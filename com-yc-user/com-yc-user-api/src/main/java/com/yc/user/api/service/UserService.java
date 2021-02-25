package com.yc.user.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.user.api.dto.UserDTO;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 18:16 2021/2/7
 */
public interface UserService {
    boolean insert(UserDTO userDTO);
    boolean updateById(UserDTO userDTO);
    boolean updateHeadimgById(UserDTO userDTO);
    boolean updateNicknameById(UserDTO userDTO);
    UserDTO getById(Long id);
    Page<UserDTO> page(Page page, UserDTO userDTO);
    List<UserDTO> list(UserDTO userDTO);
    boolean removeById(Long id);
    boolean deleteByIds(String ids);
}
