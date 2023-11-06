package com.cf.sqlTest.api.designPatterns.mediatorMode.群消息例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class QQMsgTest {
    public static void main(String[] args) {
        User jack = new User("jack");
        User rose = new User("rose");

        jack.sendMsg("hello rose");
        rose.sendMsg("hi jack, i change my mind");
    }
}
