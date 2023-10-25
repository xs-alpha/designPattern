package com.cf.sqlTest.api.designPatterns.abstractFactoryMode.baseCode.department;

import com.alibaba.fastjson.JSON;

/**
 * @author: lpy
 * @Date: 2023/10/25
 */
public class MysqlDepartment implements IDepartment{
    @Override
    public void insert(Department dep) {
        System.out.println("插入部门："+JSON.toJSONString(dep));
    }

    @Override
    public Department getDepartment(Integer id) {
        System.out.println("查询id="+id);
        return null;
    }
}
