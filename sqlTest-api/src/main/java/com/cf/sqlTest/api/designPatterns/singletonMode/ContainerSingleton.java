package com.cf.sqlTest.api.designPatterns.singletonMode;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/10/31
 * @desc: 容器单例
 * @scene: 系统中单例对象非常多的
 */
public class ContainerSingleton {
    private ContainerSingleton(){

    }
    private static Map<String, Object> singletonMap = new HashMap<String, Object>();
//    private static Map<String, Object> singletonMap = new ConcurrentHashMap<>();

    public static void putInstance(String key, Object object){
        // 这里线程不安全，多线程debug会发现，如果两个线程同时到达put那里，就会出现getInstance到对象不一样 这样的情况
        if (StringUtils.isNotBlank(key) &&
                null!=object){
            if (!singletonMap.containsKey(key)){
                singletonMap.put(key,object);
            }
        }
    }

    public static Object getInstance(String key){
        return singletonMap.get(key);
    }
}
