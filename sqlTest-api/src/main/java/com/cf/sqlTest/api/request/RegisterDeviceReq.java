package com.cf.sqlTest.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/06/27
 */
@Data
@Accessors(chain = true)
public class RegisterDeviceReq {

    @ApiModelProperty("设备名称，对应sqlTest的UUID")
    private String deviceName;

    @ApiModelProperty("备注名称，对应VIN")
    private String nickname;
}
