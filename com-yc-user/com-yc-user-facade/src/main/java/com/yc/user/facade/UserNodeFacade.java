package com.yc.user.facade;

import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.user.facade.exception.UserException;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:45 2021/2/23
 */
public interface UserNodeFacade {
    List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO) throws UserException;
}
