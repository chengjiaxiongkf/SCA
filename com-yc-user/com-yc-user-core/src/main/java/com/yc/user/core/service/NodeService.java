package com.yc.user.core.service;

import com.yc.user.core.mapper.NodeMapper;
import com.yc.user.facade.exception.UserException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 权限节点服务
 * @Date: Created in 16:46 2021/2/23
 */
@Service
public class NodeService {
    @Resource
    private NodeMapper nodeMapper;

    public List<String> getAllNode() throws UserException {
        return nodeMapper.getAllNode();
    }
}
