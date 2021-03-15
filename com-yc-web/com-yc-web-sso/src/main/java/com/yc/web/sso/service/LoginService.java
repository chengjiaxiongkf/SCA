package com.yc.web.sso.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yc.common.redis.util.RedisUtils;
import com.yc.common.util.TokenUtil;
import com.yc.web.sso.config.SsoRefreshProperties;
import com.yc.web.sso.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 20:16 2021/3/9
 */
@Service
public class LoginService {
    @Resource
    private RedisUtils redisUtils;
    @Autowired
    private SsoRefreshProperties ssoRefreshProperties;

    public String login(LoginDTO loginDTO){
        //登录
        return this.token(loginDTO);
    }

    private String token(LoginDTO loginDTO){
        //生成token
        Map<String, Object> dataMap = new HashMap<>();
        long iat = System.currentTimeMillis()/1000;
        long exp = iat + 30*3600;
        String uuid = UUID.randomUUID().toString();
        dataMap.put("user_id",300219);
        dataMap.put("role_id",0);
        dataMap.put("organize_id",5688);
        dataMap.put("platform",4);
        dataMap.put("agent_id",2);
        dataMap.put("employee_id",11358);
        dataMap.put("is_boss",1);
        dataMap.put("iat",iat);
        dataMap.put("exp",exp);
        dataMap.put("uuid",uuid);
        //准备生成token的数据
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(gson.toJson(dataMap), JsonObject.class);
        return TokenUtil.encryption(jsonObject,ssoRefreshProperties.getTokenAuthConfigSecret());    //java生成token
    }
}
