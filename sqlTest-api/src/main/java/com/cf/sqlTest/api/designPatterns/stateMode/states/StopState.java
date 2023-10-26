package com.cf.sqlTest.api.designPatterns.stateMode.states;

import com.cf.sqlTest.api.designPatterns.stateMode.VideoContext;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class StopState extends VideoState{
    @Override
    public void play() {
        super.videoContext.setVideoState(VideoContext.PLAY_STATE);
    }

    @Override
    public void stop() {
        System.out.println("停止播放视频");
    }

    @Override
    public void speed() {
        System.out.println("error:已停止视频无法倍速");
    }

    @Override
    public void paused() {
        System.out.println("error: 已停止视频无法暂停");
    }
}
