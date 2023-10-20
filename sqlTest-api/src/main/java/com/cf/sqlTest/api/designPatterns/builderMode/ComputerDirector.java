package com.cf.sqlTest.api.designPatterns.builderMode;

import com.cf.sqlTest.api.designPatterns.builderMode.builder.ComputerBuilder;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class ComputerDirector {
    public void constructComputerDirector(ComputerBuilder builder){
        builder.buildHardDisk();
        builder.buildMemory();
        builder.buildMonitor();
        builder.buildProcessor();
    }
}
