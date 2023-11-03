package com.cf.sqlTest.api.designPatterns.bridgeMode;

/**
 * @author: lpy
 * @Date: 2023/11/01
 */
public class TestBridgeMode {
    public static void main(String[] args) {
        Apple ap = new Apple();
        ap.setApk(new KugouMusic("酷狗"));
        ap.run();
        ap.setApk(new Wechat("wechat"));
        ap.run();


        IQOO iqoo = new IQOO();
        iqoo.setApk(new KugouMusic("kg"));
        iqoo.run();
        iqoo.setApk(new Wechat("xw"));
        iqoo.run();
    }
}
