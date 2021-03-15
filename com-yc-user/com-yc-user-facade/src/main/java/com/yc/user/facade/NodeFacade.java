package com.yc.user.facade;

import com.yc.user.facade.exception.UserException;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 14:37 2021/2/23
 */
public interface NodeFacade {
    List<String> getAllNode() throws UserException;
}
