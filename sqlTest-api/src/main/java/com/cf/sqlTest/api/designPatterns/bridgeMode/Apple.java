package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public class Apple extends PhoneBrand{

    @Override
    public void run() {
        System.out.println("运行:");
        software.run();
    }
}
