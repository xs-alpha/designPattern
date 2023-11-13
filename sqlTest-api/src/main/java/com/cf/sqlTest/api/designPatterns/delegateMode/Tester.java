package com.cf.sqlTest.api.designPatterns.delegateMode;

/**
 * @author: lpy
 * @Date: 2023/11/13
 */
public class Tester implements Worker {
    @Override
    public void doTask() {
        System.out.println("测试人员正在测试");
    }
}
