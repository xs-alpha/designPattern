package com.cf.sqlTest.api.designPatterns.commandMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lpy
 * @Date: 2023/11/03
 */
public class Staff {
    private List<Command> commands = new ArrayList<>();

    public void addCommands(Command c){
        commands.add(c);
    }
    public void excuteCommands(){
        commands.forEach(o->{
            o.excute();
        });
        commands.clear();
    }
}
