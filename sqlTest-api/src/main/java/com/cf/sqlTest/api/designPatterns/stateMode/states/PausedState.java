package com.cf.sqlTest.api.designPatterns.stateMode.states;

import com.cf.sqlTest.api.designPatterns.stateMode.VideoContext;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class PausedState extends VideoState{
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void stop() {
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }

    @Override
    public void speed() {
        System.out.println("error: 暂停状态无法倍速");
    }

    @Override
    public void paused() {
        System.out.println("暂停播放");
    }
}
