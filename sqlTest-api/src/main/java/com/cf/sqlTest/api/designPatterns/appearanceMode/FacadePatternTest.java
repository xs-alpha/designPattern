package com.cf.sqlTest.api.designPatterns.appearanceMode;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class FacadePatternTest {
    public static void main(String[] args) {
        SmartHomeFacade sm = new SmartHomeFacade();
        sm.leaveHome();
        sm.returnHome();
    }
}
