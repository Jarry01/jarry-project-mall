package com.tencent.mall.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.entity.OrderDetail;
import com.tencent.mall.entity.R;
import com.tencent.mall.service.IOrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端-订单详情Controller控制器
 * @author Jarry
 */
@RestController
@RequestMapping("/admin/orderDetail")
public class AdminOrderDetailController {

    @Resource
    private IOrderDetailService orderDetailService;

    /**
     * 根据订单号查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/list/{id}")
    public R listByOrderId(@PathVariable(value = "id") Integer id){
        System.out.println("id="+id);
        List<OrderDetail> orderDetailList = orderDetailService.list(new QueryWrapper<OrderDetail>().eq("mId", id));
        Map<String,Object> resultMap=new HashMap<>(4);
        resultMap.put("list",orderDetailList);
        return R.ok(resultMap);
    }

}
