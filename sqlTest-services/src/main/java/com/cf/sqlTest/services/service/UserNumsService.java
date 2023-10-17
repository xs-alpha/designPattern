package com.cf.sqlTest.services.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.sqlTest.dao.mapper.UserNumsMapper;
import com.cf.sqlTest.dao.po.UserNumsPO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author whx
 * @date 2023-08-25 10:00:55
 *
 * @description 
 */
@Service
@Slf4j
public class UserNumsService extends ServiceImpl<UserNumsMapper, UserNumsPO>  implements IService<UserNumsPO> {
    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testRequiredTrans(){
        log.info("trans--------------------------------k");
        this.save(new UserNumsPO().setDescription("requiredTrans").setNums(2).setUserId(IdUtil.getSnowflakeNextId()));
        if (1/1==1){
            throw new  Exception();
        }
    }
}

