package com.yc.user.core.service;

import com.yc.user.core.converter.UserNodeConverter;
import com.yc.user.core.entity.UserNode;
import com.yc.user.core.mapper.UserNodeMapper;
import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.user.facade.exception.UserException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:47 2021/2/23
 */
@Service
public class UserNodeService {
    @Resource
    private UserNodeMapper userNodeMapper;
    @Resource
    private UserNodeConverter userNodeConverter;

    public List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO) throws UserException {
        UserNode userNodeEntity = userNodeConverter.converUserNodeEntity(userNodeDTO);
        return userNodeConverter.converUserNodeDTOList(userNodeMapper.selectUserNode(userNodeEntity));
    }
}
