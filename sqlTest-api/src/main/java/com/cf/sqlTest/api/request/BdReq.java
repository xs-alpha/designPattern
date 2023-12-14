package com.cf.sqlTest.api.request;

import com.cf.sqlTest.api.controller.ChatCompletion;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BdReq {
    private String content;
    private String role;
}
