package com.cf.sqlTest.api.designPatterns.stateMode.states;

import com.cf.sqlTest.api.designPatterns.stateMode.VideoContext;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class PlayState extends VideoState{
    @Override
    public void play() {
        System.out.println("正常播放视频");
    }

    @Override
    public void stop() {
        super.videoContext.setVideoState(VideoContext.STOP_STATE);
    }

    @Override
    public void speed() {
        super.videoContext.setVideoState(VideoContext.SPEED_STATE);
    }

    @Override
    public void paused() {
        super.videoContext.setVideoState(VideoContext.PAUSED_STATE);

    }
}
