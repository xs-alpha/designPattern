package com.cf.sqlTest.api.designPatterns.mediatorMode.联合国例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class RSA extends Country{
    public RSA(UnitedNations un) {
        super(un);
    }

    @Override
    public void getMsg(String msg) {
        System.out.println("俄国获取信息"+msg);
    }

    @Override
    public void declareMsg(String msg) {
        un.declare(this, msg);
    }


}
