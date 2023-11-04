package com.cf.sqlTest.api.designPatterns.commandMode;

/**
 * @author: lpy
 * @Date: 2023/11/03
 */
public class PlayCommand implements Command{
    private Video video;

    public  PlayCommand(Video video) {
        this.video = video;
    }

    @Override
    public void excute() {
        video.play();
    }
}
