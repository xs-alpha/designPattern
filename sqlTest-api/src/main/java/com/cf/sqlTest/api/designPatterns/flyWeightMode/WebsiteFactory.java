package com.cf.sqlTest.api.designPatterns.flyWeightMode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lpy
 * @Date: 2023/11/06
 */
public class WebsiteFactory {
    private static Map<String,Website> map = new HashMap<>();
    public static Website registerWebsite(String key){
        if(null==map.get(key)){
            ConcreteWebsite concreteWebsite = new ConcreteWebsite(key);
            map.put(key,concreteWebsite);
            return concreteWebsite;
        }
        return map.get(key);
    }

    public static Integer getCount(){
        return map.size();
    }
}

