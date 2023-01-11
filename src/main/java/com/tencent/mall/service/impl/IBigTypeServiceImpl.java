package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.BigType;
import com.tencent.mall.mapper.BigTypeMapper;
import com.tencent.mall.service.IBigTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 商品大类Service实现类
 *
 * @author Jarry
 */
@Service("bigTypeService")
public class IBigTypeServiceImpl extends ServiceImpl<BigTypeMapper, BigType> implements IBigTypeService {

    @Resource
    private BigTypeMapper bigTypeMapper;


}
