package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 */
public class Success extends Action{
    @Override
    void getManConclusion(Man m) {
        System.out.println(m.getClass().getSimpleName()+" "+this.getClass().getSimpleName()+"时，背后多半有一个伟大的女人");
    }

    @Override
    void getWoManConclusion(Women m) {
        System.out.println(m.getClass().getSimpleName()+" "+this.getClass().getSimpleName()+"时，背后多半有一个失败的男人");
    }
}
