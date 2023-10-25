package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂模式;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.IDepartment;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.user.IUser;

/**
 * @author: lpy
 * @Date: 2023/10/24
 */
public interface IFactory {
    IUser createUser(String name);
    IDepartment createDepartment(String dep);
}
