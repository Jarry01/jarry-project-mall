package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品
 *
 * @author Jarry
 */
@TableName("t_product")
@Data
public class Product {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商品介绍图片
     */
    private String productIntroImgs;

    /**
     * 商品规格参数图片
     */
    private String productParaImgs;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品图片
     */
    private String proPic = "default.jpg";

    /**
     * 是否热门推荐商品
     */
    private boolean isHot = false;

    /**
     * 是否轮播图片商品
     */
    private boolean isSwiper = false;

    /**
     * 轮播排序
     */
    private Integer swiperSort = 0;

    /**
     * 商品轮播图片
     */
    private String swiperPic = "default.jpg";

    /**
     * 描述
     */
    private String description;

    /**
     * 商品类比
     */
    @TableField(select = false)
    private SmallType type;

    /**
     * 设置热门推荐日期时间
     */
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date hotDateTime;

    /**
     * 商品轮播图片
     */
    @TableField(select = false)
    private List<ProductSwiperImage> productSwiperImageList;

}
