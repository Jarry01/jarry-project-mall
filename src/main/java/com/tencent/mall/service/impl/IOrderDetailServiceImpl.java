package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.OrderDetail;
import com.tencent.mall.mapper.OrderDetailMapper;
import com.tencent.mall.service.IOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 订单细表Service实现类
 *
 * @author Jarry
 */
@Service("orderDetailService")
public class IOrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

    @Resource
    private OrderDetailMapper orderDetailMapper;

}
