package com.cf.sqlTest.api.designPatterns.adapterMode.接口版被适配者;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class InterfaceAdapterTest {
    public static void main(String[] args) {
        /**
         * 在这个例子中，ForeignSocketImpl实现了ForeignSocket接口，
         * Adapter适配器类仍然实现了中国插头接口，同时包装了外国插座接口。
         * 这使小明可以连接中国的插头到外国的插座，即使外国插座是一个接口。适配器模式的核心思想是将不兼容的接口适配在一起，无论这些接口是类还是接口。
         */
        ForeignSocket foreignSocket = new ForeignSocketImpl();
        Adapter adapter = new Adapter(foreignSocket);
        adapter.insertChinesePlug();
    }
}
