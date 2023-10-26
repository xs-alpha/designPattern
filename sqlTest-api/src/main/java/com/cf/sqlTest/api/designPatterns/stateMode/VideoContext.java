package com.cf.sqlTest.api.designPatterns.stateMode;

import com.cf.sqlTest.api.designPatterns.stateMode.states.*;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public class VideoContext {
    public VideoState videoState;

    public static final PlayState PLAY_STATE = new PlayState();
    public static final StopState STOP_STATE = new StopState();
    public static final PausedState PAUSED_STATE = new PausedState();
    public static final SpeedState SPEED_STATE = new SpeedState();

    public VideoState getVideoState() {
        return videoState;
    }

    public void setVideoState(VideoState videoState) {
        this.videoState = videoState;
        // 因为继承自抽象类的playstate,pausedState等类在设置super.videoContext.setVideoState的时候是需要用到的
        this.videoState.setVideoContext(this);
    }

    public void play(){
        this.videoState.play();
    }
    public void stop(){
        this.videoState.stop();
    }
    public void paused(){
        this.videoState.paused();
    }
    public void speed(){
        this.videoState.speed();
    }
}
