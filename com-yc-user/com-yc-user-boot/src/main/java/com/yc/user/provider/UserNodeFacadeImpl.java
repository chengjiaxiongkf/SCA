package com.yc.user.provider;

import com.yc.user.core.service.UserNodeService;
import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.user.core.converter.UserNodeConverter;
import com.yc.user.facade.exception.UserException;
import com.yc.user.facade.UserNodeFacade;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:47 2021/2/23
 */
@DubboService
public class UserNodeFacadeImpl implements UserNodeFacade {
    @Resource
    private UserNodeService userNodeService;
    @Resource
    private UserNodeConverter userNodeConverter;
    @Override
    public List<UserNodeDTO> selectUserNode(UserNodeDTO userNodeDTO) throws UserException {
        return userNodeService.selectUserNode(userNodeDTO);
    }
}
