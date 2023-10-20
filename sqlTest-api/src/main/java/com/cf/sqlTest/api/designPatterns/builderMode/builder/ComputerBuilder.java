package com.cf.sqlTest.api.designPatterns.builderMode.builder;

import com.cf.sqlTest.api.designPatterns.builderMode.product.Computer;

/**
 * @author: lpy
 * @Date: 2023/10/20
 * @desc: 学的不要太死，可以用抽象类哦
 */
public interface ComputerBuilder {
    void buildProcessor();
    void buildMemory();
    void buildHardDisk();
    void buildMonitor();
    Computer getResult();
}
