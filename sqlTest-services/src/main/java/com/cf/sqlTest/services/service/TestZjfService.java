package com.cf.sqlTest.services.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cf.sqlTest.dao.po.TestZjfPO;
import com.cf.sqlTest.dao.mapper.TestZjfMapper;
import org.springframework.stereotype.Service;

/**
 * @author whx
 * @date 2023-08-23 13:56:27
 *
 * @description 
 */
@Service
public class TestZjfService extends ServiceImpl<TestZjfMapper, TestZjfPO>  implements IService<TestZjfPO> {

}
