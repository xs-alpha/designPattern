package com.cf.sqlTest.api.designPatterns.mediatorMode.群消息例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 * @desc: 中介者
 */
public class QQGroup {
    public static void sendMsg(User u, String msg){
        System.out.println("["+u.getName()+"]:"+msg);
    }
}
