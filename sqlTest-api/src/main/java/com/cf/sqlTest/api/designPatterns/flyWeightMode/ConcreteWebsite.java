package com.cf.sqlTest.api.designPatterns.flyWeightMode;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public class ConcreteWebsite extends Website{
    private String webName;
    public ConcreteWebsite(String name){
        this.webName = name;
    }

    @Override
    public void use(User u) {
        System.out.println("用户"+u.getName()+"< id:"+u.getId()+"使用网站"+webName);
    }
}
