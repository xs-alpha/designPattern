package com.cf.sqlTest.api.designPatterns.responsibilityChainMode;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/11/04
 */
@Data
@Accessors(chain = true)
public class Request {
    private String name;
    private Integer num;
    private Integer level;
}
