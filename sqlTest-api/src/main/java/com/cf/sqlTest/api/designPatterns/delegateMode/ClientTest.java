package com.cf.sqlTest.api.designPatterns.delegateMode;

/**
 * @author: lpy
 * @Date: 2023/11/13
 */
public class ClientTest {
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.setWorker(new Tester());
        manager.delegateTask();

        manager.setWorker(new Devloper());
        manager.delegateTask();
    }
}
