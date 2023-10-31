package com.cf.sqlTest.api.designPatterns.singletonMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author: lpy
 * @Date: 2023/10/31
 */
public class LazySingletonPreventReflectAttack {
    private static LazySingletonPreventReflectAttack lazySingletonPreventReflectAttack;
    // 注意，一定是static
    private static boolean flag = true;

    private LazySingletonPreventReflectAttack(){
        if (flag) {
            flag = false;
        }else {
            throw new RuntimeException("禁止反射创建单例");
        }
    }
    public static LazySingletonPreventReflectAttack getInstance(){
        if (null==lazySingletonPreventReflectAttack){
            lazySingletonPreventReflectAttack = new LazySingletonPreventReflectAttack();
        }
        return lazySingletonPreventReflectAttack;
    }

    public static void main(String[] args) throws Exception{
        LazySingletonPreventReflectAttack instance = LazySingletonPreventReflectAttack.getInstance();

        // 下面的通过flag可以解决反射创建单例的问题，
        Class<LazySingletonPreventReflectAttack> attackClass = LazySingletonPreventReflectAttack.class;
        Constructor<LazySingletonPreventReflectAttack> declaredConstructor = attackClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
//        declaredConstructor.newInstance();

        // 但是也可以通过反射改flag字段突破,所以这种懒汉式加载无论逻辑多复杂，都能通过反射轻易突破
        Field flag = attackClass.getDeclaredField("flag");
        flag.setAccessible(true);
        flag.set(attackClass, true);
        LazySingletonPreventReflectAttack reflectAttack = declaredConstructor.newInstance();
        System.out.println(reflectAttack.hashCode()==instance.hashCode());
    }
}
