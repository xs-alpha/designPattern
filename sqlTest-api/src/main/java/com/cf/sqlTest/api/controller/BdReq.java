package com.cf.sqlTest.api.controller;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BdReq {
    private String role;

    private String content;
}
