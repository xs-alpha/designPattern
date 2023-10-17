package com.cf.sqlTest.dao.po;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


import java.util.Date;

/**
 * @author whx
 * @date 2023-08-25 10:00:55
 *
 * @description 
 */
@Data
@TableName("user_nums")
@Accessors(chain = true)
public class UserNumsPO  {


    /**
     * 
     */
    private Long userId;

    /**
     * 次数
     */
    private Integer nums;

    /**
     * 备注
     */
    private String description;
}
