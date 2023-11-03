package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public abstract class PhoneBrand {
    protected ApkSoftware software;

    public void setApk(ApkSoftware software){
        this.software = software;
    }


    public abstract void run();
}
