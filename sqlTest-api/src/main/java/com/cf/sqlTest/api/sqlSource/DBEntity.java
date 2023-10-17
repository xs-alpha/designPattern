package com.cf.sqlTest.api.sqlSource;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/09/11
 */
@Data
@Accessors(chain = true)
public class DBEntity {
    private Integer id;

    private String url;

    private String password;

    private String userName;
}
