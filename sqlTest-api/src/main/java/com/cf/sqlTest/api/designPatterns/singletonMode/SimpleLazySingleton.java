package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class SimpleLazySingleton {
    private static  SimpleLazySingleton simpleLazySingleton;

    private SimpleLazySingleton(){

    }

    // 多线程debug会发现，这样线程不安全，改进的话可以加synchronized,加在静态方法上相当于锁的是这个类的class文件,如果不是静态方法相当于锁的是在堆内存中生成的对象
    // synchronized锁太重了
    public static  SimpleLazySingleton getInstance(){
        if (null==simpleLazySingleton){
            simpleLazySingleton = new SimpleLazySingleton();
        }
        return simpleLazySingleton;
    }
}
