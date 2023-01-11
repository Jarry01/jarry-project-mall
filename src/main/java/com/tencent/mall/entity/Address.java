package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * 订单主表
 *
 * @author Jarry
 */
@TableName("t_address")
@Data
public class Address {

    /**
     * 编号
     */
    private Integer addrId;

    /**
     * openId微信用户ID
     */
    private String userId;

    /**
     * 地址
     */
    private String addr;

    /**
     * 收货人
     */
    private String userName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 订单创建日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date createDate;

    /**
     * 是否默认 0是, 1否
     */
    private String checked;

}
