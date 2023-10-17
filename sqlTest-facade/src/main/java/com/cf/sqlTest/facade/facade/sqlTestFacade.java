package com.cf.sqlTest.facade.facade;

import com.aliyun.iot20180120.models.QueryDeviceInfoResponseBody;
import com.aliyun.iot20180120.models.RegisterDeviceResponse;
import com.cf.sqlTest.facade.dto.sqlTestClientRegionParamDTO;
import com.cf.sqlTest.facade.dto.RegisterDeviceDTO;

/**
 * @author: lpy
 * @Date: 2023/06/27
 */
public interface sqlTestFacade {

    /**
     * 查询设备信息
     *
     * @param regionParamDTO
     * @param pDeviceName
     * @return
     */
    QueryDeviceInfoResponseBody queryDeviceInfo(sqlTestClientRegionParamDTO regionParamDTO, String pDeviceName);

    /**
     * 注册设备
     *
     * @param regionParamDTO
     * @param param
     */
    RegisterDeviceResponse registerDevice(sqlTestClientRegionParamDTO regionParamDTO, RegisterDeviceDTO param);
}
