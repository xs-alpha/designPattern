package com.cf.sqlTest.dao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private Integer id;
    private String name;
    private String address;
    private Integer age;
    private String sex;
    private String hobby;
}
