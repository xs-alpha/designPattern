package com.cf.sqlTest.api.designPatterns.adapterMode.类版被适配者_类适配器模式;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class SocketAdapter extends ForeignSocket implements ChinesePlug {

    @Override
    public void insertChinesePlug(){
        System.out.println("[-*-]:正使用适配器链接，将中国插头插入外国插座");
        // 这里强调的是通过继承
        super.insertForeignSocket();
    }
}
