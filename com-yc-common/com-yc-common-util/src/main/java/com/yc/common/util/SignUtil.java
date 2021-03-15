package com.yc.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * @author: WangYang
 * @Date: 2020/11/1 10:57
 * @Description:
 */


@Slf4j
public class SignUtil {

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 验证签名结果
     */
    public static boolean verifySign(SortedMap<String, Object> params, String key) {
        Object sign = params.get("sign");
        log.info("Url Sign : {}", sign);
        if (sign==null || StringUtils.isEmpty(sign.toString())) {
            return false;
        }
        //把参数加密
        String paramsSign = getParamsSign(params,key);
        log.info("Param Sign : {}", paramsSign);
        return !StrUtil.isEmpty(paramsSign) && sign.equals(paramsSign);
    }

    /**
     * @param params 所有的请求参数都会在这里进行排序加密
     * @return 得到签名
     */
    public static String getParamsSign(SortedMap<String, Object> params,String key) {
        //要先去掉 Url 里的 Sign
        params.remove("sign");
        String timestamp = params.get("timestamp").toString();
        String paramsJsonStr = JSONObject.toJSONString(params);
        String skey = timestamp + key;
        String md5Key = "key=" + SecureUtil.md5(skey);
        String paramsSign = paramsJsonStr + md5Key;
        return SecureUtil.md5(paramsSign);
    }

}
