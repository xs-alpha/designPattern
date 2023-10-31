package com.cf.sqlTest.api.designPatterns.singletonMode;

import java.lang.reflect.Constructor;

/**
 * @author: lpy
 * @Date: 2023/10/31
 * @desc: 反射攻击，演示,有一个前提，在类加载就把对象创建好了这种情况是ok的，静态内部类的单例也可以用下面的方法避免反射攻击
 */
public class ReflectAttackTest {
    public static void main(String[] args) throws Exception {
        // 正常方式获取instance
        //反射攻击，演示,有一个前提，在类加载就把对象创建好了这种情况是ok的，静态内部类的单例也可以用下面的方法避免反射攻击
        HungrySingleton instance = HungrySingleton.getInstance();
        StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();

        // 骚操作开始
        Class<HungrySingleton> hungrySingletonClass = HungrySingleton.class;
        Constructor<HungrySingleton> declaredConstructor = hungrySingletonClass.getDeclaredConstructor();
        // 由于是私有化的，这样直接访问是不可以的,需要设置访问权限
        declaredConstructor.setAccessible(true);
        HungrySingleton hungrySingleton = declaredConstructor.newInstance();

        System.out.println(hungrySingleton.hashCode()==instance.hashCode());
    }
}
