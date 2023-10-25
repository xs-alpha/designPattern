package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
public class SqlserverUser implements IUser{
    @Override
    public void insert(User u) {
        System.out.println("插入："+u.toString());
    }

    @Override
    public User getUser(String name) {
        System.out.println("getUser:"+name);
        return null;
    }
}
