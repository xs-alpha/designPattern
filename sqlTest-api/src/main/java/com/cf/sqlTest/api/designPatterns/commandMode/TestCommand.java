package com.cf.sqlTest.api.designPatterns.commandMode;

/**
 * @author: lpy
 * @Date: 2023/11/03
 */
public class TestCommand {
    public static void main(String[] args) {
        Staff staff = new Staff();
        Video video = new Video("视频");

        staff.addCommands(new PlayCommand(video));
        staff.addCommands(new PlayCommand(video));
        staff.addCommands(new StopCommand(video));

        staff.excuteCommands();
    }
}
