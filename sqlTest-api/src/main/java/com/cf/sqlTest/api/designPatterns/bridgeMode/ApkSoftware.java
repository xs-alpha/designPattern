package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public abstract class ApkSoftware {
    protected String name;
    public ApkSoftware(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract void run();
}
