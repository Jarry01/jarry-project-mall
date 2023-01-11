package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详细表
 *
 * @author Jarry
 */
@TableName("t_order_detail")
@Data
public class OrderDetail {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 订单主表Id
     */
    private Integer mId;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品购买数量
     */
    private Integer goodsNumber;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPic;

}
