package com.cf.sqlTest.facade.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author whx
 * @date 2023/8/23
 */
@Data
@Accessors(chain = true)
public class SaveDeviceDTO {
	/**
	 * 设备名称，对应sqlTest的UUID
	 */
	private String deviceName;

	/**
	 * xxx_xxx，_后面的对应VIN车架号
	 */
	private String nickname;
}
