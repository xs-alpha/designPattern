package com.cf.sqlTest.api.designPatterns.stateMode.states;

import com.cf.sqlTest.api.designPatterns.stateMode.VideoContext;

/**
 * @author: lpy
 * @Date: 2023/10/26
 */
public abstract class VideoState {
    protected VideoContext videoContext;

    public void setVideoContext(VideoContext v){
        this.videoContext = v;
    }

    // 这几个方法一定是抽象的
    public abstract void play();
    public abstract void stop();
    public abstract void speed();
    public abstract void paused();
}
