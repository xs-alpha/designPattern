package com.cf.sqlTest.api.designPatterns.mediatorMode.联合国例子;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
public abstract class Country {
   protected UnitedNations un;
   public Country(UnitedNations un){
      this.un = un;
   }

   public abstract  void getMsg(String msg);
   public abstract  void declareMsg(String msg);
}
