package com.yc.user.core.service;

import com.yc.user.api.dto.UserNodeDTO;
import com.yc.user.api.service.UserNodeService;
import com.yc.user.core.converter.UserNodeConverter;
import com.yc.user.core.dao.UserNodeDao;
import com.yc.user.core.entity.UserNodeEntity;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:47 2021/2/23
 */
@DubboService
public class UserNodeServiceImpl implements UserNodeService {
    @Resource
    private UserNodeDao userNodeDao;
    @Resource
    private UserNodeConverter userNodeConverter;
    @Override
    public List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO) {
        UserNodeEntity userNodeEntity = userNodeConverter.converUserNodeEntity(userNodeDTO);
        return userNodeConverter.converUserNodeDTOList(userNodeDao.selectUserNode(userNodeEntity));
    }
}
