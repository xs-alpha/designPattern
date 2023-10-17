package com.cf.sqlTest.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author whx
 * @date 2023/6/27
 */
@Data
public class QueryDeviceReq {

	@ApiModelProperty("设备名称(UUID)")
	@NotBlank(message = "设备名称(UUID)不能为空")
	private String deviceName;

}
