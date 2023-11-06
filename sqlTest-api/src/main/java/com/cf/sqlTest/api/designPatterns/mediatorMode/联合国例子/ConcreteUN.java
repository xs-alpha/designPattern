package com.cf.sqlTest.api.designPatterns.mediatorMode.联合国例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 * @desc: 具体中介者
 */
public class ConcreteUN extends UnitedNations{
    private USA usa;
    private RSA rsa;
    // 联合国安理会了解所有的国家，所以拥有美国俄罗斯等的属性
    public void setUsa(USA u){
        this.usa = u;
    }
    public void setRsa(RSA r){
        this.rsa = r;
    }

    @Override
    public void declare(Country c, String msg) {
        // 重写了声明方法，实现了两个对象间的通信
        if (c==this.usa){
            this.rsa.getMsg(msg);
        }else if (c==this.rsa){
            this.usa.getMsg(msg);
        }
    }
}
