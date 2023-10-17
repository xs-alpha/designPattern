package com.cf.sqlTest.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author whx
 * @date 2023-08-23 13:48:19
 * @description sqlTest设备车架号信息表
 */
@Data
@TableName("uip_sqlTest_device")
@Accessors(chain = true)
public class UipsqlTestDevicePO {


    /**
     * 主键id
     */
    @TableId(value = "uip_sqlTest_device_id", type = IdType.INPUT)
    private Long uipsqlTestDeviceId;

    /**
     * 设备名称(UUID)
     */
    private String deviceName;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 车型
     */
    private String carType;

    /**
     * 创建时间
     */
    private Date createTm;
}
