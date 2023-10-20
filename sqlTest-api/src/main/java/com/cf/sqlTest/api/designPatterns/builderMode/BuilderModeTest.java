package com.cf.sqlTest.api.designPatterns.builderMode;

import com.cf.sqlTest.api.designPatterns.builderMode.builder.ComputerBuilder;
import com.cf.sqlTest.api.designPatterns.builderMode.builder.HighConfigurationComputerBuilder;
import com.cf.sqlTest.api.designPatterns.builderMode.builder.LowConfigurationComputerBuilder;

/**
 * @author: lpy
 * @Date: 2023/10/20
 */
public class BuilderModeTest {
    public static void main(String[] args) {
        // 高配电脑
        ComputerBuilder hc = new HighConfigurationComputerBuilder();
        // 低配
        ComputerBuilder lc = new LowConfigurationComputerBuilder();

        ComputerDirector computerDirector = new ComputerDirector();

        computerDirector.constructComputerDirector(hc);
        computerDirector.constructComputerDirector(lc);

        System.out.println(hc.getResult());
        System.out.println(lc.getResult());
    }
}
