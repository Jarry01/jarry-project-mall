package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 产品轮播图片
 *
 * @author Jarry
 */
@TableName("t_product_swiper_image")
@Data
public class ProductSwiperImage {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 图片名称
     */
    private String image;

    /**
     * 排列序号 从小到大排序
     */
    private Integer sort;

    /**
     * 所属产品
     */
    private Integer productId;


}
