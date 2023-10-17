package com.cf.sqlTest.api.designPatterns.decorateMode.detailFinery;

import com.cf.sqlTest.api.designPatterns.decorateMode.Clothes;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 草帽
 */
public class StrwHat extends Clothes {

    @Override
    public void show(){
        System.out.print("带着草帽");

        super.show();
    }
}

