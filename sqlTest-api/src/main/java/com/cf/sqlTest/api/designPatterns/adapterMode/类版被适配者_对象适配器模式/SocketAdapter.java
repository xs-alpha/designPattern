package com.cf.sqlTest.api.designPatterns.adapterMode.类版被适配者_对象适配器模式;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class SocketAdapter implements ChinesePlug{
    private ForeignSocket foreignSocket;

    public SocketAdapter(ForeignSocket foreignSocket){
        this.foreignSocket = foreignSocket;
    }

    @Override
    public void insertChinesePlug(){
        System.out.println("[-*-]:正使用适配器链接，将中国插头插入外国插座");
        // 这里强调的是通过组合方式
        foreignSocket.insertForeignSocket();
    }
}
