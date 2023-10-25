package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂模式;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.IDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.SqlserverDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.IUser;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.SqlserverUser;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
public class SqlserverFactory implements IFactory{
    @Override
    public IUser createUser(String name) {
        return new SqlserverUser();
    }

    @Override
    public IDepartment createDepartment(String dep) {
        return new SqlserverDepartment();
    }
}
