package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂_反射;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.IDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.IUser;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
public class DataAccess {
    private static final String assemblyName = "com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.";
    private static final String assemblyDepName = "com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.";

    private static final String db = "Sqlserver";

    public static IUser createUser(){
        return (IUser)getInstance(assemblyName+db+"User");
    }

    public static IDepartment createDepartment(){
        return (IDepartment)getInstance(assemblyDepName+db+"Department");
    }

    public static Object getInstance(String className){
        Object o = null;
        try {
            o = Class.forName(className).getDeclaredConstructor().newInstance();
        }catch (Exception e){
            System.out.println("error:\n"+e.getMessage());
            o = null;
        }
        return o;
    }
    
}
