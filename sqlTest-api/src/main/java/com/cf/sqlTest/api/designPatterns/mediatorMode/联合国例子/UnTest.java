package com.cf.sqlTest.api.designPatterns.mediatorMode.联合国例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public class UnTest {
    public static void main(String[] args) {
        ConcreteUN concreteUN = new ConcreteUN();
        RSA rsa = new RSA(concreteUN);
        USA usa = new USA(concreteUN);

        concreteUN.setRsa(rsa);
        concreteUN.setUsa(usa);

        usa.declareMsg(":美国国家原子能研究中心人员名单");
        rsa.declareMsg(":俄罗斯太空部署战略");
    }
}
