package com.tencent.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.entity.BigType;
import com.tencent.mall.entity.Product;
import com.tencent.mall.entity.R;
import com.tencent.mall.entity.SmallType;
import com.tencent.mall.service.IBigTypeService;
import com.tencent.mall.service.IProductService;
import com.tencent.mall.service.ISmallTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品大类控制器
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/bigType")
public class BigTypeController {

    @Resource
    private IBigTypeService bigTypeService;

    @Resource
    private ISmallTypeService smallTypeService;

    @Resource
    private IProductService productService;

    /**
     * 查询所有商品大类
     *
     * @return
     */
    @RequestMapping("/findAll")
    public R findAll() {
        List<BigType> bigTypeList = bigTypeService.list(new QueryWrapper<BigType>().orderByAsc("id"));
        Map<String, Object> map = new HashMap<>();
        map.put("message", bigTypeList);
        return R.ok(map);
    }

    /**
     * 获取所有菜单信息
     *
     * @return
     */
    @RequestMapping("/findCategories")
    public R findCategories() {
        List<BigType> bigTypeList = bigTypeService.list(new QueryWrapper<BigType>().orderByAsc("id"));
        for (BigType bigType : bigTypeList) {
            List<SmallType> smallTypeList = smallTypeService.list(new QueryWrapper<SmallType>().eq("bigTypeId", bigType.getId()));
            bigType.setSmallTypeList(smallTypeList);
            for (SmallType smallType : smallTypeList) {
                List<Product> productList = productService.list(new QueryWrapper<Product>().eq("typeId", smallType.getId()));
                smallType.setProductList(productList);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("message", bigTypeList);
        return R.ok(map);
    }


}
