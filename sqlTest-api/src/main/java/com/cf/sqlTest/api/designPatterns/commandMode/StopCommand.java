package com.cf.sqlTest.api.designPatterns.commandMode;

/**
 * @author: lpy
 * @Date: 2023/11/03
 */
public class StopCommand implements Command{
    private Video video;
    public StopCommand(Video v){
        this.video = v;
    }

    @Override
    public void excute() {
        video.stop();
    }
}
