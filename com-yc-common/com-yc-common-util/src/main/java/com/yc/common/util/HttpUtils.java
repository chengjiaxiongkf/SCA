package com.yc.common.util;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.yc.common.base.enums.AppTypeEnum;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author: WangYang
 * @Date: 2020/11/1 10:59
 * @Description:
 */
public class HttpUtils {

    public static final String GET = "GET";

    /**
     * 将URL的参数和body参数合并
     * @author show
     * @date 14:24 2019/5/29
     * @param request
     */
    public static SortedMap<String, Object> getAllParams(HttpServletRequest request) throws IOException {

        SortedMap<String, Object> result = new TreeMap<>();
        //获取URL上的参数
        if (GET.equals(request.getMethod())) {
            Map<String, String> urlParams = getUrlParams(request);
            for (Map.Entry entry : urlParams.entrySet()) {
                result.put((String) entry.getKey(), entry.getValue());
            }
        }
        Map<String, String> allRequestParam = new HashMap<>(16);
        // get请求不需要拿body参数
        if (!GET.equals(request.getMethod())) {
            allRequestParam = getAllRequestParam(request);
        }
        //将URL的参数和body参数进行合并
        if (allRequestParam != null) {
            for (Map.Entry entry : allRequestParam.entrySet()) {
                result.put((String) entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    /**
     * 获取 Body 参数
     * @author show
     * @date 15:04 2019/5/30
     * @param request
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String str = "";
        StringBuilder wholeStr = new StringBuilder();
        //一行一行的读取body体里面的内容；
        while ((str = reader.readLine()) != null) {
            wholeStr.append(str);
        }
        //转化成json对象
        return JSONObject.parseObject(wholeStr.toString(),LinkedHashMap.class, Feature.OrderedField);
    }

    /**
     * 将URL请求参数转换成Map
     * @author show
     * @param request
     */
    public static Map<String, String> getUrlParams(HttpServletRequest request) {

        Map<String, String[]> resultMap = request.getParameterMap();
        Map<String, String> result = new HashMap<>(16);

        for (Map.Entry<String, String[]> entry : resultMap.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue()[0]);
            result.put(entry.getKey(), entry.getValue()[0]);
        }
        return result;
    }

    public static AppTypeEnum getAppType(HttpServletRequest request){
        String clientType = request.getHeader("x-client-type");
        if(clientType==null){
            String clientOS = request.getHeader("x-client-os");
            if(clientOS!=null){
                String[] OSArr = clientOS.split(";");
                if(OSArr[0].equalsIgnoreCase("ios")){
                    return AppTypeEnum.IOS;
                }else{
                    return AppTypeEnum.ANDROID;
                }
            }
            return null;
        }else {
            if(clientType.equalsIgnoreCase("miniprogram")){
                return AppTypeEnum.MINI_PROGRAM;
            }else if(clientType.equalsIgnoreCase("h5")||clientType.equalsIgnoreCase("web")){
                return AppTypeEnum.WEB;
            }else if(clientType.equalsIgnoreCase("android")){
                return AppTypeEnum.ANDROID;
            }else if(clientType.equalsIgnoreCase("ios")){
                return AppTypeEnum.IOS;
            }else{
                String clientOS = request.getHeader("x-client-os");
                if(clientOS!=null){
                    String[] OSArr = clientOS.split(";");
                    if(OSArr[0].equalsIgnoreCase("ios")){
                        return AppTypeEnum.IOS;
                    }else{
                        return AppTypeEnum.ANDROID;
                    }
                }
                return null;
            }
        }
    }

    public static <T> void out(ServletResponse response, T result){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONUtil.parse(result).toJSONString(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
