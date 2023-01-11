package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.SmallType;
import com.tencent.mall.mapper.SmallTypeMapper;
import com.tencent.mall.service.ISmallTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 商品小类Service实现类
 *
 * @author Jarry
 */
@Service("smallTypeService")
public class ISmallTypeServiceImpl extends ServiceImpl<SmallTypeMapper, SmallType> implements ISmallTypeService {

    @Resource
    private SmallTypeMapper SmallTypeMapper;


    @Override
    public List<SmallType> list(Map<String, Object> map) {
        return SmallTypeMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return SmallTypeMapper.getTotal(map);
    }

    @Override
    public Integer add(SmallType smallType) {
        return SmallTypeMapper.add(smallType);
    }

    @Override
    public Integer update(SmallType smallType) {
        return SmallTypeMapper.update(smallType);
    }
}
