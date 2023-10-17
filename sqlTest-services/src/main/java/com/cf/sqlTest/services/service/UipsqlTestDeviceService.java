package com.cf.sqlTest.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.sqlTest.dao.mapper.UipsqlTestDeviceMapper;
import com.cf.sqlTest.dao.po.UipsqlTestDevicePO;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2023-08-23 13:48:19
 * @description sqlTest设备车架号信息表
 */
@Service
public class UipsqlTestDeviceService extends ServiceImpl<UipsqlTestDeviceMapper, UipsqlTestDevicePO> implements IService<UipsqlTestDevicePO> {

}

