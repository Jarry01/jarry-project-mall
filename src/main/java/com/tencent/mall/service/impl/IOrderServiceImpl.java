package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.Order;
import com.tencent.mall.mapper.OrderMapper;
import com.tencent.mall.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 订单主表Service实现类
 *
 * @author Jarry
 */
@Service("orderService")
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public List<Order> list(Map<String, Object> map) {
        return orderMapper.list(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return orderMapper.getTotal(map);
    }
}
