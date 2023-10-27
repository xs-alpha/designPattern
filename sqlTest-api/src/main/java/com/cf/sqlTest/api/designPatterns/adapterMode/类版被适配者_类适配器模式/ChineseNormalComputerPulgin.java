package com.cf.sqlTest.api.designPatterns.adapterMode.类版被适配者_类适配器模式;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class ChineseNormalComputerPulgin implements ChinesePlug{
    @Override
    public void insertChinesePlug() {
        System.out.println("中国普通电脑插头");
    }
}
