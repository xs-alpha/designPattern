package com.cf.sqlTest.api.designPatterns.builderMode.builder;

import com.cf.sqlTest.api.designPatterns.builderMode.product.Computer;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class LowConfigurationComputerBuilder implements ComputerBuilder{
    private Computer computer = new Computer();

    @Override
    public void buildProcessor() {
       computer.setProcessor("i3 1200f");
    }

    @Override
    public void buildMemory() {
        computer.setMemory("16G");
    }

    @Override
    public void buildHardDisk() {
        computer.setHardDisk("1T");
    }

    @Override
    public void buildMonitor() {
        computer.setMonitor("熊猫牌小显示器");
    }

    @Override
    public Computer getResult() {
        return this.computer;
    }
}
