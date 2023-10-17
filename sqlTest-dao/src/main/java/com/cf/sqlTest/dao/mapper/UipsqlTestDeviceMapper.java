package com.cf.sqlTest.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cf.sqlTest.dao.po.UipsqlTestDevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author whx
 * @date 2023-08-23 13:48:19
 * @description sqlTest设备车架号信息表
 */
@Mapper
public interface UipsqlTestDeviceMapper extends BaseMapper<UipsqlTestDevicePO> {

}

