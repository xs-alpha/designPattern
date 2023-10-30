package com.cf.sqlTest.api.designPatterns.compositeMode;

import java.util.ArrayList;

/**
 * @author: lpy
 * @Date: 2023/10/30
 */
public class ConcreteCompany extends Company{
    protected ArrayList<Company> children = new ArrayList<Company>();

    public ConcreteCompany(String name) {
        super(name);
    }

    @Override
    public void add(Company company) {
        children.add(company);
    }

    @Override
    public void remove(Company company) {
        children.remove(company);
    }

    @Override
    public void display(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("-");
        }
        System.out.println(this.name);
        for (Company child : children) {
            child.display(depth+2);
        }
    }

    @Override
    public void lineDuty() {
        for (Company child : children) {
            child.lineDuty();
        }
    }
}
