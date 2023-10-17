package com.cf.sqlTest.services.facade.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.cf.sqlTest.dao.po.UipsqlTestDevicePO;
import com.cf.sqlTest.dao.po.UserNumsPO;
import com.cf.sqlTest.facade.dto.SaveDeviceDTO;
import com.cf.sqlTest.facade.facade.UipsqlTestFacade;
import com.cf.sqlTest.facade.result.Result;
import com.cf.sqlTest.services.service.UipsqlTestDeviceService;
import com.cf.sqlTest.services.service.UserNumsService;
import com.cf.sqlTest.services.utils.BeanConvertorUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author whx
 * @date 2023/8/23
 */
@Service
@Slf4j
public class UipsqlTestFacadeImpl implements UipsqlTestFacade {

    @Resource
    private UipsqlTestDeviceService uipsqlTestDeviceService;

    @Override
    public Result saveDevice(SaveDeviceDTO param) {
        log.info("saveDevice,param: {}", JSON.toJSONString(param));
        String[] vin = param.getNickname().split("_");
        if (vin.length != 2) {
            return Result.buildErrorResult("车架号格式错误");
        }
        UipsqlTestDevicePO uipsqlTestDevicePO = BeanConvertorUtils.map(param, UipsqlTestDevicePO.class).setCarType(vin[0]).setVin(vin[1]);
        log.info("uipsqlTestDevicePO: {}", JSON.toJSONString(uipsqlTestDevicePO));
        long tm = System.currentTimeMillis();
        boolean result = uipsqlTestDeviceService.save(uipsqlTestDevicePO);
        log.info("saveDevice,result: {},rt={}", result, (System.currentTimeMillis() - tm));

        return Result.buildSuccessResult();
    }

    /*0---------------------------*/

    @Resource
    private UserNumsService userNumsService;

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTrans() {
        log.info("trans--------------------------------k");
        userNumsService.save(new UserNumsPO().setDescription("test hello1").setNums(1).setUserId(IdUtil.getSnowflakeNextId()));
        try {
            userNumsService.testRequiredTrans();
        }catch (Exception e){
            log.info("捕捉到子事务异常");
        }
    }


}
