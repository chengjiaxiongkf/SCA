package com.yc.web.sso.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.yc.common.redis.util.RedisUtils;
import com.yc.common.util.SecurityConstant;
import com.yc.common.util.TokenUtil;
import com.yc.mall.facade.MallShopFacade;
import com.yc.mall.facade.dto.MallShopDTO;
import com.yc.user.facade.UserAuthFacade;
import com.yc.user.facade.dto.UserAuthInfoDTO;
import com.yc.user.facade.dto.UserNodeDTO;
import com.yc.web.sso.config.SsoRefreshProperties;
import com.yc.web.sso.dto.AuthDTO;
import com.yc.web.sso.exception.SsoException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:47 2021/3/4
 */
@Service
@Slf4j
public class InterfaceAuthService {
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private SsoRefreshProperties ssoRefreshProperties;

    @DubboReference
    private MallShopFacade mallShopFacade;

    @DubboReference
    private UserAuthFacade userAuthFacade;

    public void process(AuthDTO authDTO) throws SsoException {
        //解析token
        UserAuthInfoDTO userAuthInfoDTO = parseToken(authDTO.getToken());
        String userAuthInfoDTOStr;
        userAuthInfoDTO.setMenuId(authDTO.getMenuId());//封装请求的菜单Id
        userAuthInfoDTO = this.getUserAuthInfo(userAuthInfoDTO);//获取接口权限跟超级管理员标识
        this.setRedis(authDTO.getToken(),userAuthInfoDTO);
        //当前用户是超级管理员 直接下一步
        if (userAuthInfoDTO.isAdmin()) {
            return;
        }
        //是否拥有接口权限
        if (!this.checkUrl(userAuthInfoDTO, authDTO)) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.URL_AUTH_FAIL);
        }
    }
    /**
     * @Author: ChengJiaXiong
     * @Description: 获取用户授权信息
     * @Date: Created in 19:56 2021/3/9
     */
    private UserAuthInfoDTO getUserAuthInfo(UserAuthInfoDTO userAuthInfoDTO) {
        return userAuthFacade.getUserAuthority(userAuthInfoDTO);
    }
    private UserAuthInfoDTO parseToken(String token) throws SsoException {
        UserAuthInfoDTO userAuthInfoDTO;
        Long userId;
        String shopNo;
        Integer organizeId;
        Integer platform;
        if (!redisUtils.hasKey(token)) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.TOKEN_NO_EXIST);
        }
        redisUtils.set(token,"1",RedisUtils.HOURS); //更新redis中token的过期时间
        String tokenValue = redisUtils.get(token).toString();
        if(StringUtils.isEmpty(tokenValue)){
            throw new SsoException(SsoException.SsoExceptionCodeEnum.TOKEN_VALID);
        }
        if (!"1".equals(tokenValue)){//已经被解析过了
            return com.alibaba.fastjson.JSONObject.parseObject(tokenValue,UserAuthInfoDTO.class);
        }
        JSONObject jsonObject;
        jsonObject = TokenUtil.verifyToken(token, ssoRefreshProperties.getTokenAuthConfigSecret());
        if (jsonObject == null) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.TOKEN_VALID);
        }
        userId = jsonObject.getLong("user_id");
        if (userId == null || userId == 0) {  //错误的token
            throw new SsoException(SsoException.SsoExceptionCodeEnum.TOKEN_VALID);
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
            log.warn("setShopNo error:",e);
        }
        return userAuthInfoDTO;
    }
    /**
     * @Author: ChengJiaXiong
     * @Description:  设置该用户的商城编码
     * @Date: Created in 19:55 2021/3/9
     */
    private void setShopNo(UserAuthInfoDTO userAuthInfoDTO) {
        String shopNo = userAuthInfoDTO.getShopNo();
        int organizeId = userAuthInfoDTO.getOrganizeId();
        if ((StrUtil.isEmpty(shopNo) || SecurityConstant.DEFAULT_SHOP_NO.equals(shopNo)) && organizeId != 0) {
            MallShopDTO mallShopDTO = new MallShopDTO();
            mallShopDTO.setOrganizeId(organizeId);
            //TODO redis
            MallShopDTO mallShop = mallShopFacade.getOne(mallShopDTO);
            if (null != mallShop) {
                userAuthInfoDTO.setShopNo(mallShop.getShopNo());
            }
        }
    }
    /**
     * 解析结果放入redis
     * @param key
     * @param authenticationDTO
     */
    public void setRedis(String key,UserAuthInfoDTO authenticationDTO){
        String userAuthInfoDTOStr = com.alibaba.fastjson.JSONObject.toJSONString(authenticationDTO);
        log.info("userAuthInfoDTOStr:"+userAuthInfoDTOStr);
        redisUtils.set(key, userAuthInfoDTOStr, RedisUtils.HOURS);
    }
    private boolean checkParams(AuthDTO authenticationDTO) throws SsoException {
        if (authenticationDTO.getMenuId()==null || authenticationDTO.getMenuId()==0) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.MENU_ID_NULL);
        }
        if (StringUtils.isEmpty(authenticationDTO.getMethod())) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.METHOD_NULL);
        }
        if (StringUtils.isEmpty(authenticationDTO.getToken())) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.TOKEN_NULL);
        }
        if (StringUtils.isEmpty(authenticationDTO.getUrl())) {
            throw new SsoException(SsoException.SsoExceptionCodeEnum.URL_NULL);
        }
        return true;
    }
    private boolean checkUrl(UserAuthInfoDTO userAuthInfoDTO, AuthDTO authenticationDTO) {
        List<UserNodeDTO> list = userAuthInfoDTO.getNodes();
        return list.stream().filter(obj -> obj.getMethodType().equals(authenticationDTO.getMethod()))
                .filter(obj -> obj.getUri().equals(authenticationDTO.getUrl()))
                .collect(Collectors.toList()).size() > 0;
    }
}
