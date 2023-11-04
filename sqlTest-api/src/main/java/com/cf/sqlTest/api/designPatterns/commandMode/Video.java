package com.cf.sqlTest.api.designPatterns.commandMode;

/**
 * @author: lpy
 * @Date: 2023/11/03
 * @desc: 相当于书中waiter服务员的角色
 */
public class Video {
    private String name;
    public Video(String name){
        this.name = name;
    }
    public void play(){
        System.out.println(name+"播放");
    }
    public void stop(){
        System.out.println(name+"停止播放");
    }
}
