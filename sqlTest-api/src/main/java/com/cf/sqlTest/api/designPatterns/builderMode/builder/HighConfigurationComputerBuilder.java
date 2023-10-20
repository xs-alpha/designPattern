package com.cf.sqlTest.api.designPatterns.builderMode.builder;

import com.cf.sqlTest.api.designPatterns.builderMode.product.Computer;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class HighConfigurationComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void buildProcessor() {
       computer.setProcessor("i9 1300k");
    }

    @Override
    public void buildMemory() {
        computer.setMemory("256G");
    }

    @Override
    public void buildHardDisk() {
        computer.setHardDisk("40T");
    }

    @Override
    public void buildMonitor() {
        computer.setMonitor("Apple 视网膜屏");
    }

    @Override
    public Computer getResult() {
        return this.computer;
    }
}
