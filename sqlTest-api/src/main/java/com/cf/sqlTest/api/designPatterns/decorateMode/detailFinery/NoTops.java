package com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery;

import com.cf.sqlTest.api.designPatterns.decorateMode.Clothes;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 么有上衣
 */
public class NoTops extends Clothes {

    @Override
    public void show() {
        System.out.print("光着膀子");

        super.show();
    }
}
