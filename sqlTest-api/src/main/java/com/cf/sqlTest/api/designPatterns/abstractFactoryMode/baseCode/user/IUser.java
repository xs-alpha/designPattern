package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
public interface IUser {
    void insert(User u);
    User getUser(String name);
}
