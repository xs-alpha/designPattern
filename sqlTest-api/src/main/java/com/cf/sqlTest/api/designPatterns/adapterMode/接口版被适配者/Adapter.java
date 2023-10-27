package com.cf.sqlTest.api.designPatterns.adapterMode.接口版被适配者;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class Adapter implements ChinesePlug{
    private ForeignSocket foreignSocket;

    public Adapter(ForeignSocket f){
        this.foreignSocket = f;
    }


    @Override
    public void insertChinesePlug() {
        System.out.println("【~~】通过适配器将中国插头插入外国插座");
        this.foreignSocket.insertForeignSocket();
    }
}
