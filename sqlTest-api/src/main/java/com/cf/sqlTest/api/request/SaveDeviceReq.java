package com.cf.sqlTest.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author whx
 * @date 2023/8/23
 */
@Data
public class SaveDeviceReq {
	@ApiModelProperty("设备名称，对应sqlTest的UUID")
	@NotBlank(message = "设备名称不能为空")
	private String deviceName;

	@ApiModelProperty("xxx_xxx，_后面的对应VIN车架号")
	@NotBlank(message = "车架号不能为空")
	private String nickname;
}
