package com.cf.sqlTest.api.designPatterns.adapterMode.类版被适配者_对象适配器模式;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class Client {
    public static void main(String[] args) {
        ForeignSocket foreignSocket = new ForeignSocket();
        SocketAdapter socketAdapter = new SocketAdapter(foreignSocket);

        socketAdapter.insertChinesePlug();
    }
}
