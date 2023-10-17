package com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery;

import com.cf.sqlTest.api.designPatterns.decorateMode.Clothes;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class BlackShoes extends Clothes {

    @Override
    public void show() {
        System.out.print("穿着嘿鞋子");

        super.show();
    }
}
