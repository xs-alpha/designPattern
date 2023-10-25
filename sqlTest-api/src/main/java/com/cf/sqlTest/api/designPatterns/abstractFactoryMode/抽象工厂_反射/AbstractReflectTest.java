package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.抽象工厂_反射;

import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.Department;
import com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department.IDepartment;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
public class AbstractReflectTest {

    public static void main(String[] args) {
        Department dep = new Department(1, "ac", "h/3");
        IDepartment department = DataAccess.createDepartment();

        department.getDepartment(1);
        department.insert(dep);
    }
}
