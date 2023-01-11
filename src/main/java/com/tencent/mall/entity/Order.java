package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 *
 * @author Jarry
 */
@TableName("t_order")
@Data
public class Order {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * openId微信用户ID
     */
    private String userId;

    /**
     * 微信用户
     */
    @TableField(select = false)
    private WxUserInfo wxUserInfo;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 联系电话
     */
    private String telNumber;

    /**
     * 订单创建日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date createDate;

    /**
     * 订单支付日期
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date payDate;

    /**
     * 订单状态 1 未支付 2 已经支付/待收货 3 退款/退货
     */
    private Integer status = 1;

    /**
     * 订单商品详情
     */
    @TableField(select = false, exist = false)
    private OrderDetail[] goods;

}
