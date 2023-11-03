package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public class Wechat extends ApkSoftware{
    public Wechat(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println(name+"运行了");
    }
}
