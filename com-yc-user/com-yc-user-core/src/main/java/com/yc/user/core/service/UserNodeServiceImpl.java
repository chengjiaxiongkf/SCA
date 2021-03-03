package com.yc.user.core.service;

import com.yc.user.api.dto.UserNodeDTO;
import com.yc.common.base.exception.UserException;
import com.yc.user.api.service.UserNodeService;
import com.yc.user.core.converter.UserNodeConverter;
import com.yc.user.core.entity.UserNodeEntity;
import com.yc.user.core.mapper.UserNodeMapper;
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
    private UserNodeMapper userNodeMapper;
    @Resource
    private UserNodeConverter userNodeConverter;
    @Override
    public List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO) throws UserException {
        UserNodeEntity userNodeEntity = userNodeConverter.converUserNodeEntity(userNodeDTO);
        return userNodeConverter.converUserNodeDTOList(userNodeMapper.selectUserNode(userNodeEntity));
    }
}
