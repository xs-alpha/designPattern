package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂模式;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.IUser;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.User;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
public class Test1 {
    public static void main(String[] args) {
        User u = new User(1, "xiaomi", 15);

        IFactory ia = new SqlserverFactory();
        // 切换的话，只需要这么改
        IFactory im = new MysqlFactory();

        IUser iu = ia.createUser("xiaox");

        iu.getUser("xiaom");
        iu.insert(u);

    }
}
