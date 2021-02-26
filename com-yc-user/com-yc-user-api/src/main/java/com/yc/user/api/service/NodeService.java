package com.yc.user.api.service;
import com.yc.common.base.exception.UserException;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 14:37 2021/2/23
 */
public interface NodeService {
    List<String> getAllNode() throws UserException;
}
