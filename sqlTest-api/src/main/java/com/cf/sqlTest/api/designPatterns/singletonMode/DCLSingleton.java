package com.cf.sqlTest.api.designPatterns.singletonMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 * @desc: 线程安全的
 */
public class DCLSingleton {
    // 加volatile防止指令重排
    private static volatile DCLSingleton dclSingleton;

    private DCLSingleton(){

    }

    public static DCLSingleton getInstance(){
        if (null == dclSingleton){
            synchronized (DCLSingleton.class){
                if (null == dclSingleton){
                    dclSingleton = new DCLSingleton();
                    // 这一行代码有三个步骤
                    // 1.分配内存给这个对象
                    // 2.初始化对象
                    // 3.设置dclSingleton指向刚分配的内存
                }
            }
        }
        return dclSingleton;
    }
}
