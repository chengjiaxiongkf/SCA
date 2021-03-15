package com.yc.user.provider;

import com.yc.user.core.service.NodeService;
import com.yc.user.facade.exception.UserException;
import com.yc.user.facade.NodeFacade;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description: 权限节点服务
 * @Date: Created in 16:46 2021/2/23
 */
@DubboService
public class NodeFacadeImpl implements NodeFacade {
    @Autowired
    private NodeService nodeService;

    @Override
    public List<String> getAllNode() throws UserException {
        return nodeService.getAllNode();
    }
}
