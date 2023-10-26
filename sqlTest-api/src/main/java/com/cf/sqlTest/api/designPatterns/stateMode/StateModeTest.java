package com.cf.sqlTest.api.designPatterns.stateMode;

import com.cf.sqlTest.api.designPatterns.stateMode.states.PlayState;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class StateModeTest {
    public static void main(String[] args) {
        VideoContext videoContext = new VideoContext();
        videoContext.setVideoState(new PlayState());
        System.out.println(videoContext.getVideoState().getClass().getSimpleName());
        // 有读者说“你难道没发现，真正的目标方法内的语句并没有输出吗“，可以看这个语句，以及输出，就明白了
        videoContext.play();
        System.out.println("---------1-----------");

        System.out.println("---------2-----------");
        videoContext.stop();
        System.out.println(videoContext.getVideoState().getClass().getSimpleName());
        System.out.println("---------2-----------");

        System.out.println("---------3-----------");
        videoContext.paused();
        System.out.println(videoContext.getVideoState().getClass().getSimpleName());
        System.out.println("---------3-----------");

        System.out.println("---------4-----------");
        videoContext.stop();
        System.out.println(videoContext.getVideoState().getClass().getSimpleName());
        System.out.println("---------4-----------");

        videoContext.speed();
        System.out.println(videoContext.getVideoState().getClass().getSimpleName());
    }
}
