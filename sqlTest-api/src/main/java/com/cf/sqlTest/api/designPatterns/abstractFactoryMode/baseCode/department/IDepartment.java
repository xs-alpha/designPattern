package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
public interface IDepartment {
    void insert(Department dep);
    Department getDepartment(Integer id);
}
