package com.cf.sqlTest.api.designPatterns.compositeMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class CompositeModeTest {
    public static void main(String[] args) {
        ConcreteCompany root = new ConcreteCompany("总公司");
        root.add(new Department("开发部"));
        root.add(new Department("安全部"));
        ConcreteCompany y1 = new ConcreteCompany("云科技分公司");
        root.add(y1);
        y1.add(new Department("逆向部"));
        y1.add(new Department("js部"));

        ConcreteCompany y2 = new ConcreteCompany("云科技分公司2");
        root.add(y2);
        y2.add(new Department("天文部"));
        y2.add(new Department("app部"));

        ConcreteCompany y3 = new ConcreteCompany("云科技分公司3");
        root.add(y3);
        ConcreteCompany y4 = new ConcreteCompany("云科技分部门3-`1");
        y3.add(y4);
        y4.add(new Department("天文部"));
        y4.add(new Department("app部"));

        root.display(0);
    }
}
