package com.cf.sqlTest.facade.facade;

import com.cf.sqlTest.facade.dto.SaveDeviceDTO;
import com.cf.sqlTest.facade.result.Result;

/**
 * @author whx
 * @date 2023/8/23
 */
public interface UipsqlTestFacade {
	/**
	 * 保存sqlTest车架号信息
	 *
	 * @param param
	 */
	Result saveDevice(SaveDeviceDTO param);

	void testTrans();
}
