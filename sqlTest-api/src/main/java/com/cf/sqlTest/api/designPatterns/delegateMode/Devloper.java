package com.cf.sqlTest.api.designPatterns.delegateMode;

/**
 * @author: lpy
 * @Date: 2023/11/13
 */
public class Devloper implements Worker{
    @Override
    public void doTask() {
        System.out.println("开发人员正在写bug");
    }
}
