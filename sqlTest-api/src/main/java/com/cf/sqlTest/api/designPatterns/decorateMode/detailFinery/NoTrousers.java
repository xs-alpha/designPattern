package com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery;

import com.cf.sqlTest.api.designPatterns.decorateMode.Clothes;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 没有裤子
 */
public class NoTrousers extends Clothes {

    @Override
    public void show() {
        System.out.print("光着屁股");

        super.show();
    }
}
