package com.cf.sqlTest.api.designPatterns.decorateMode;

/**
 * @author: lpy
 * @Date: 2023/09/14
 * @desc: 服装类
 */
public class Clothes implements ICharacter{
    private ICharacter component;

    public void decorate(ICharacter component){
        this.component = component;
    }

    @Override
    public void show() {
        if (null != this.component){
            this.component.show();
        }
    }
}
