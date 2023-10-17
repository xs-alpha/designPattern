package com.cf.sqlTest.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/06/27
 */
@Data
@Accessors(chain = true)
public class sqlTestClientRegionParamDTO {
    private String productKey ;

    private String iotInstanceId;

    private String regionId;
}
