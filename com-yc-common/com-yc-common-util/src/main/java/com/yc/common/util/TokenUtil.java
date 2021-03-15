package com.yc.common.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.google.gson.JsonObject;

/**
 * @author: WangYang
 * @Date: 2020/9/23 17:20
 * @Description:
 */
public class TokenUtil {
    /**
     * 验签token
     * @param token
     * @return
     */
    public static JSONObject verifyToken(String token,String secret) throws JSONException {
        String decodeStr = Base64.decodeStr(token);
        JSONObject json = new JSONObject(decodeStr,true);
        String jtiStr = json.get("jti").toString();
        String iatStr = json.get("iat").toString();
        String zanbKey = SecureUtil.md5(iatStr + secret);
        json.remove("jti");
        String verifyStr = json.toString() + "key=" + zanbKey;
        String jtiResult = SecureUtil.md5(verifyStr);
        if(jtiStr.equals(jtiResult)){
            return json;
        }
        return null;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 加密token
     * @Date: Created in 11:44 2021/3/5
     * @Param: jsonObject.addProperty("user_id", userId);
     *         jsonObject.addProperty("iat", iat);
     *         jsonObject.addProperty("exp", exp);
     *         jsonObject.addProperty("role", role);
     *         jsonObject.addProperty("uuid", uuid);
     */
    public static String encryption(JsonObject jsonObject, String tokenSecret) throws JSONException {
        jsonObject.addProperty("jti",getJti(jsonObject,tokenSecret));
        return Base64.encode(jsonObject.toString(),"utf-8");
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 生成一个jti
     * @Date: Created in 14:09 2021/3/5
     * @Params: [{'user_id':data['user_id'], 'iat': iat, 'exp': exp, 'role':data['role'], 'uuid':params['uuid']},""]
     */
    private static String getJti(JsonObject jsonObject,String tokenSecret){
        String tempSecret = SecureUtil.md5(jsonObject.get("iat").toString()+tokenSecret);
        String finalSecret = SecureUtil.md5(jsonObject.toString()+"key="+tempSecret);
        return finalSecret;
    }
}
