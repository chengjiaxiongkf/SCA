package com.yc.authentication.core.handler;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.yc.authentication.api.dto.AuthenticationDTO;
import com.yc.common.base.exception.AuthException;
import com.yc.common.base.enums.AuthExceptionCodeEnum;
import com.yc.authentication.core.config.AuthRefreshProperties;
import com.yc.authentication.core.util.AuthUtils;
import com.yc.common.config.redis.util.RedisUtil;
import com.yc.common.util.SecurityConstant;
import com.yc.common.util.TokenUtil;
import com.yc.mall.api.dto.MallShopDTO;
import com.yc.mall.api.service.MallShopService;
import com.yc.user.api.dto.UserAuthInfoDTO;
import com.yc.user.api.dto.UserNodeDTO;
import com.yc.user.api.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 14:55 2021/2/23
 */
@Component
@Slf4j
public class InterfaceAuthHandler extends AbstractAuthHandler {
    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private AuthRefreshProperties authenticationRefreshProperties;

    @DubboReference
    private MallShopService mallShopService;

    @DubboReference
    private UserAuthService userAuthService;

    @Override
    public AuthEnum type() {
        return AuthEnum.INTERFACE;
    }

    @Override
    protected boolean checkParams(AuthenticationDTO authenticationDTO) throws AuthException {
        if (authenticationDTO.getMenuId()==null || authenticationDTO.getMenuId()==0) {
            return false;
        }
        if (StringUtils.isEmpty(authenticationDTO.getMethod())) {
            return false;
        }
        if (StringUtils.isEmpty(authenticationDTO.getToken())) {
            return false;
        }
        if (StringUtils.isEmpty(authenticationDTO.getUrl())) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean check(AuthenticationDTO authenticationDTO) throws AuthException {
        UserAuthInfoDTO userAuthInfoDTO = parseToken(authenticationDTO.getToken());
        //不是U客后台/U客APP的不需要校验
        if (!AuthUtils.checkPlatform(authenticationRefreshProperties, userAuthInfoDTO.getPlatform())) {
            return true;
        }
        userAuthInfoDTO.setMenuId(authenticationDTO.getMenuId()); //封装请求的菜单Id
        userAuthInfoDTO = this.getUserAuthInfo(userAuthInfoDTO);   //获取权限角色信息
        String userAuthInfoDTOStr = com.alibaba.fastjson.JSONObject.toJSONString(userAuthInfoDTO);
        log.info("userAuthInfoDTOStr:"+userAuthInfoDTOStr);
        //最新的用户授权信息放入token的value中
        redisUtil.set(authenticationDTO.getToken(), userAuthInfoDTOStr , RedisUtil.HOURS);
        //当前用户是超级管理员 直接下一步
        if (userAuthInfoDTO.isAdmin()) {
            return true;
        }
        //是否拥有接口权限
        if (this.checkUrl(userAuthInfoDTO, authenticationDTO)) {
            return true;
        }
        return false;
    }


    private boolean checkUrl(UserAuthInfoDTO userAuthInfoDTO, AuthenticationDTO authenticationDTO) {
        List<UserNodeDTO> list = userAuthInfoDTO.getNodes();
        return list.stream().filter(obj -> obj.getMethodType().equals(authenticationDTO.getMethod()))
                            .filter(obj -> obj.getUri().equals(authenticationDTO.getUrl()))
                .collect(Collectors.toList()).size() > 0;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 获取用户权限信息
     * @Date: Created in 14:16 2021/2/23
     */
    private UserAuthInfoDTO getUserAuthInfo(UserAuthInfoDTO userAuthInfoDTO) {
        //TODO caffer+redis缓存双重缓存
        return userAuthService.getUserAuthority(userAuthInfoDTO);
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 解析token
     * @Date: Created in 15:55 2021/2/9
     */
    private UserAuthInfoDTO parseToken(String token) throws AuthException {
        UserAuthInfoDTO userAuthInfoDTO;
        Long userId;
        String shopNo;
        Integer organizeId;
        Integer platform;
        if (!redisUtil.hasKey(token)) {
            throw new AuthException(AuthExceptionCodeEnum.AU003);
        }
        String tokenValue = redisUtil.get(token).toString();
        redisUtil.set(token, tokenValue, RedisUtil.HOURS); //更新redis中token的过期时间
        if(StringUtils.isEmpty(tokenValue)){
            throw new AuthException(AuthExceptionCodeEnum.AU001);
        }
        if (!"1".equals(tokenValue)){
            return com.alibaba.fastjson.JSONObject.parseObject(tokenValue,UserAuthInfoDTO.class);
        }
        JSONObject jsonObject;
        jsonObject = TokenUtil.verifyToken(token, authenticationRefreshProperties.getTokenAuthConfigSecret());
        if (jsonObject == null) {
            throw new AuthException(AuthExceptionCodeEnum.AU001);
        }
        userId = jsonObject.getLong("user_id");
        if (userId == null || userId == 0) {  //错误的token
            throw new AuthException(AuthExceptionCodeEnum.AU001);
        }
        shopNo = jsonObject.getStr("shop_no");
        organizeId = jsonObject.getInt("organize_id");
        platform = jsonObject.getInt("platform");
        //数据封装
        userAuthInfoDTO = new UserAuthInfoDTO();
        userAuthInfoDTO.setUserId(userId);
        userAuthInfoDTO.setShopNo(shopNo);
        userAuthInfoDTO.setOrganizeId(organizeId);
        userAuthInfoDTO.setPlatform(platform);
        try{
            this.setShopNo(userAuthInfoDTO);
        }catch (Exception e){ //封装ShopNo不影响授权
            log.error("setShopNo error:",e);
        }
        return userAuthInfoDTO;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description:    封装shopNo
     * @Date: Created in 13:27 2021/2/24
     */
    private void setShopNo(UserAuthInfoDTO userAuthInfoDTO){
        String shopNo = userAuthInfoDTO.getShopNo();
        int organizeId = userAuthInfoDTO.getOrganizeId();
        if ((StrUtil.isEmpty(shopNo) || SecurityConstant.DEFAULT_SHOP_NO.equals(shopNo)) && organizeId != 0) {
            MallShopDTO mallShopDTO = new MallShopDTO();
            mallShopDTO.setOrganizeId(organizeId);
            //TODO redis
            MallShopDTO mallShop = mallShopService.getOne(mallShopDTO);
            if (null != mallShop) {
                userAuthInfoDTO.setShopNo(mallShop.getShopNo());
            }
        }
    }
}
