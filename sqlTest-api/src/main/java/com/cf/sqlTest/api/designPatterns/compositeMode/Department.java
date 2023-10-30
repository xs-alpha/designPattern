package com.cf.sqlTest.api.designPatterns.compositeMode;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class Department extends Company{
    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(this.name);
    }

    @Override
    public void lineDuty() {
        super.lineDuty();
    }

    public Department(String name) {
        super(name);
    }
}
