package com.cf.sqlTest.api.designPatterns.adapterMode.接口版被适配者;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class ForeignSocketImpl implements ForeignSocket{
    @Override
    public void insertForeignSocket() {
        System.out.println("插入外国的大插座~~");
    }
}
