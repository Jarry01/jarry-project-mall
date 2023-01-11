package com.tencent.mall.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.entity.Order;
import com.tencent.mall.entity.OrderDetail;
import com.tencent.mall.entity.PageBean;
import com.tencent.mall.entity.R;
import com.tencent.mall.service.IOrderDetailService;
import com.tencent.mall.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端-订单Controller控制器
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IOrderDetailService orderDetailService;

    /**
     * 根据条件分页查询
     *
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestBody PageBean pageBean) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("orderNo", pageBean.getQuery().trim());
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Order> list = orderService.list(map);
        Long total = orderService.getTotal(map);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("orderList", list);
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    /**
     * 更新订单状态
     *
     * @param order
     * @return
     */
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody Order order) {
        Order resultOrder = orderService.getById(order.getId());
        resultOrder.setStatus(order.getStatus());
        orderService.saveOrUpdate(resultOrder);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        // 删除订单细表的数据
        orderDetailService.remove(new QueryWrapper<OrderDetail>().eq("mId", id));
        orderService.removeById(id);
        return R.ok();
    }

}
