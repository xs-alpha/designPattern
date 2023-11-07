package com.cf.sqlTest.api.designPatterns.visitorMode;

/**
 * @author: lpy
 * @Date: 2023/11/07
 */
public class Man extends Person{
    /**
     * 需要提一下当中用到一种双分派的技术，首先在客户程序中将具体
     * 状态作为参数传递给'男人'类完成了一次分派，然后'男人'类调用作为参数
     * 的'具体状态'中的方法'男人反应'，同时将自己（this）作为参数传递进
     * 去。这便完成了第二次分派。双分派意味着得到执行的操作决定于请求的种
     * 类和两个接收者的类型。'接受'方法就是一个双分派的操作，它得到执行的
     * 操作不仅决定于'状态'类的具体状态，还决定于它访问的'人'的类别。
     * @param c
     */
    @Override
    void accept(Action c) {
        c.getManConclusion(this);
    }
}
