package com.cf.sqlTest.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: lpy
 * @Date: 2023/06/27
 */
@Data
@Accessors(chain = true)
public class RegisterDeviceDTO {

    /**
     * 设备名称，对应sqlTest的UUID
     */
    private String DeviceName;

    /**
     * 备注名称，对应VIN
     */
    private String Nickname;
}
