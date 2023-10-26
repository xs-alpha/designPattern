package com.cf.sqlTest.api.designPatterns.stateMode.states;

import com.cf.sqlTest.api.designPatterns.stateMode.VideoContext;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class SpeedState extends VideoState{
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void stop() {
        System.out.println("正常播放");
    }

    @Override
    public void speed() {
        System.out.println("倍速播放");
    }

    @Override
    public void paused() {
        super.videoContext.setVideoState(VideoContext.PAUSED_STATE);
    }
}
