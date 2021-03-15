package com.yc.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 自定义断言工具类。封装自定义异常信息
 *
 * @author SanGang
 */
public final class AssertUtils {

    private AssertUtils() {
    }

    /**
     * 不是true则抛异常
     *
     * @param expression bool表达式
     * @param message    错误信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }
    /**
     * 不是空则抛异常
     *
     * @param object  对象信息
     * @param message 错误信息
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RuntimeException(message);
        }
    }
    /**
     * 字符串不为空则抛异常
     *
     * @param text    字符串信息
     * @param message 错误信息
     */
    public static void isBlank(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * Integer为空或o
     * @param value    字符串信息
     * @param message 错误信息
     */
    public static void isBlank(Integer value, String message) {
        if (value==null || value==0) {
            throw new RuntimeException(message);
        }
    }

    /**
     * Integer为空或o
     * @param value    字符串信息
     * @param message 错误信息
     */
    public static void isBlank(Long value, String message) {
        if (value==null || value==0L) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 数组为空则抛异常
     *
     * @param array   数组
     * @param message 错误信息
     */
    public static <T> void isEmpty(T[] array, String message) {
        if (ArrayUtils.isEmpty(array)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 集合为空则抛异常
     *
     * @param collection 集合
     * @param message    错误信息
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * map为空则抛异常
     *
     * @param map     map
     * @param message 错误消息
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (MapUtil.isEmpty(map)) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 正则验证
     *
     * @param regExp  正则表达式
     * @param text    字符串
     * @param message 消息
     */
    public static void matches(String regExp, String text, String message) {
        if (!Pattern.matches(regExp, text)) {
            throw new RuntimeException(message);
        }
    }
}
