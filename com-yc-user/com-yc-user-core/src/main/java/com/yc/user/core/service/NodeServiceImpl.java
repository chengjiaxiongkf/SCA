package com.yc.user.core.service;

import com.yc.common.base.exception.UserException;
import com.yc.user.api.service.NodeService;
import com.yc.user.core.mapper.NodeMapper;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:    权限节点服务
 * @Date: Created in 16:46 2021/2/23
 */
@DubboService
public class NodeServiceImpl implements NodeService {
    @Resource
    private NodeMapper nodeMapper;

    @Override
    public List<String> getAllNode() throws UserException {
        return nodeMapper.getAllNode();
    }
}
