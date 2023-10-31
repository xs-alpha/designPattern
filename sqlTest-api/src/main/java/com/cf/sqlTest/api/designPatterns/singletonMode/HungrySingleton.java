package com.cf.sqlTest.api.designPatterns.singletonMode;

import java.io.Serializable;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class HungrySingleton implements Serializable {
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    /** 私有构造函数*/
    private HungrySingleton(){
        if (null!=hungrySingleton){
            throw new RuntimeException("单例构造器禁止反射");
        }
    }

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    // 序列化破坏的对抗
    public Object readResolve(){
        return hungrySingleton;
    }
}
