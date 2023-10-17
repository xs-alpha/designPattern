package com.cf.sqlTest.api.designPatterns.decorateMode.decorateTest.ShoppingSaleSystemImprove;

/**
 * @author: lpy
 * @Date: 2023/10/16
 */
public class CashContext2 {
    private ISale cs;

    public CashContext2(int type){
        switch (type){
            case 1:
                this.cs = new CashNormal2();
                break;
            case 5:
                // 先打8折，在满300-100
                CashNormal2 cn =new CashNormal2();
                CashReturn2 cr = new CashReturn2(100d, 300d);
                CashRebate2 cre = new CashRebate2(0.8d);

                cr.setComponent(cn);
                cre.setComponent(cr);
                this.cs = cre;
                break;
            case 6:
                // 先满200再返50，最后打7折
                CashNormal2 cn2 =new CashNormal2();
                CashRebate2 cre2 = new CashRebate2(0.7d);
                CashReturn2 cr2 = new CashReturn2(50d, 200d);
                cre2.setComponent(cn2);
                cr2.setComponent(cre2);
                this.cs = cr2;
                break;
        }
    }

    public double getResult(double price, int num){
        return this.cs.acceptCash(price,num);
    }
}
