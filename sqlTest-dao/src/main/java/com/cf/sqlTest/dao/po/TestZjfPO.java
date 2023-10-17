package com.cf.sqlTest.dao.po;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2023-08-23 13:56:27
 *
 * @description 
 */
@Data
@TableName("test_zjf")
@Accessors(chain = true)
public class TestZjfPO  {


    /**
     * 
     */
    private String value;
}
