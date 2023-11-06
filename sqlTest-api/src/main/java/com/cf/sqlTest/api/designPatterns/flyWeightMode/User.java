package com.cf.sqlTest.api.designPatterns.flyWeightMode;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/11/06
 * @desc: 用户类，用于网站的客户账号，是"网站"类的外部状态。
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private Integer id;
}
