package com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery;

import com.cf.sqlTest.api.designPatterns.decorateMode.Clothes;

/**
 * @author: lpy
 * @Date: 2023/09/14
 */
public class Tshirts extends Clothes {


    @Override
    public void show(){
        System.out.print("穿着T恤");

        super.show();
    }
}
