package com.cf.sqlTest.api.designPatterns.adapterMode.类版被适配者_类适配器模式;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class Client {
    public static void main(String[] args) {
        SocketAdapter socketAdapter = new SocketAdapter();

        socketAdapter.insertChinesePlug();
    }
}
