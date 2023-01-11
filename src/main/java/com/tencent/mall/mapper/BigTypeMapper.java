package com.tencent.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.mall.entity.BigType;


/**
 * 商品大类Mapper接口
 *
 * @author Jarry
 */
public interface BigTypeMapper extends BaseMapper<BigType> {

    /**
     * 根据id查询商品大类
     *
     * @param id
     * @return
     */
    public BigType findById(Integer id);

}
