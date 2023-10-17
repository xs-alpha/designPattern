package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

/**
 * @author: lpy
 * @Date: 2023/10/16
 */
public class SaleSystemTest {
    public static void main(String[] args) {
        CashContext2 cashContext2 = new CashContext2(5);
        System.out.println(cashContext2.getResult(500, 2));
    }


}
