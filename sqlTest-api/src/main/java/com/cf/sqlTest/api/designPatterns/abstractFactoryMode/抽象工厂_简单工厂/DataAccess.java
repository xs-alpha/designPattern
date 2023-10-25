package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂_简单工厂;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.IDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.MysqlDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.SqlserverDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.IUser;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.MysqlUser;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.SqlserverUser;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
public class DataAccess {
    /**
     *  抛 弃 了 IFactory 、 SqlserverFactory 和
     * AccessFactory三个工厂类，取而代之的是DataAccess类，由于事先设置了
     * db的值（Sqlserver或Access），所以简单工厂的方法都不需要输入参数，这
     * 样 在 客 户 端 就 只 需 要 DataAccess.createUser （ ） 和
     * DataAccess.createDepartment（）来生成具体的数据库访问类实例，客户端
     * 没有出现任何一个SQL Server或Access的字样，达到了解耦的目的
     */

    private static final String name = "mysql";

    public static IUser createUser(){
        IUser user = null;
        switch (name){
            case "sqlserver":
                user = new SqlserverUser();
                break;
            case "mysql":
                user = new MysqlUser();

        }
        return user;
    }

    public static IDepartment createDepartment(){
        IDepartment dep = null;
        switch (name){
            case "sqlserver":
                dep = new SqlserverDepartment();
                break;
            case "mysql":
                dep = new MysqlDepartment();

        }
        return dep;
    }
}

