package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.ProductSwiperImage;
import com.tencent.mall.mapper.ProductSwiperImageMapper;
import com.tencent.mall.service.IProductSwiperImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品Service实现类
 *
 * @author Jarry
 */
@Service("productSwiperImageService")
public class IProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageMapper, ProductSwiperImage> implements IProductSwiperImageService {

    @Resource
    private ProductSwiperImageMapper productSwiperImageMapper;


}
