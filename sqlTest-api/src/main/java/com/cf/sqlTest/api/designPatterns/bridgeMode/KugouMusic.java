package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public class KugouMusic extends ApkSoftware{
    public KugouMusic(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println(name+"运行了");
    }
}
