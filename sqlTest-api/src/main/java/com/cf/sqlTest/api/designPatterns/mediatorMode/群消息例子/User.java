package com.cf.sqlTest.api.designPatterns.mediatorMode.群消息例子;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
@Data
public class User {
    private String name;

    public User(String name){
        this.name = name;
    }

    public void sendMsg(String msg){
        QQGroup.sendMsg(this,msg);
    }
}
