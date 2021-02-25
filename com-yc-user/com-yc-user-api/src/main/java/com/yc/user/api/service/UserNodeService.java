package com.yc.user.api.service;

import com.yc.user.api.dto.UserNodeDTO;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:45 2021/2/23
 */
public interface UserNodeService {
    List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO);
}
