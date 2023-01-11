package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.Product;
import com.tencent.mall.mapper.ProductMapper;
import com.tencent.mall.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品Service实现类
 *
 * @author Jarry
 */
@Service("productService")
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findSwiper() {
        return productMapper.findSwiper();
    }

    @Override
    public List<Product> findHot() {
        return productMapper.findHot();
    }

    @Override
    public List<Product> list(Map<String, Object> map) {
        return productMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return productMapper.getTotal(map);
    }

    @Override
    public Integer add(Product product) {
        return productMapper.add(product);
    }

    @Override
    public Integer update(Product product) {
        return productMapper.update(product);
    }
}
