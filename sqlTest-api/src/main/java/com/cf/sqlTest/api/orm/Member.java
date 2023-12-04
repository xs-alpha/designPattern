package com.cf.sqlTest.api.orm;

import lombok.Data;

/**
 * @author: lpy
 * @Date: 2023/12/04
 */
@SelfOrmTableName("member")
@Data
public class Member {
    private Integer id;
    private String name;
    private String address;

    @SelfOrmTableField(value = "ageu")
    private Integer age;
}
