package com.tencent.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.mall.entity.Order;
import com.tencent.mall.entity.OrderDetail;
import com.tencent.mall.entity.R;
import com.tencent.mall.properties.WeixinpayProperties;
import com.tencent.mall.service.IOrderDetailService;
import com.tencent.mall.service.IOrderService;
import com.tencent.mall.util.DateUtil;
import com.tencent.mall.util.JwtUtils;
import com.tencent.mall.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.util.*;

/**
 * 订单Controller控制器
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/my/order/")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IOrderDetailService orderDetailService;

    @Resource
    private WeixinpayProperties weixinpayProperties;


    /**
     * 创建订单，返回订单号
     *
     * @return
     */
    @RequestMapping("/create")
    public R create(@RequestBody Order order, @RequestHeader(value = "token") String token) {
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                String openId = claims.getId();
                order.setUserId(openId);
                order.setOrderNo(DateUtil.getCurrentDateStr());
                order.setCreateDate(new Date());
            } else {
                return R.error(500, "鉴权失败！");
            }
        } else {
            return R.error(500, "无权限访问！");
        }


        OrderDetail[] goods = order.getGoods();
        orderService.save(order);
        String orderNo = order.getOrderNo();

        for (OrderDetail orderDetail : goods) {
            orderDetail.setMId(order.getId());
            orderDetailService.save(orderDetail);
        }
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("orderNo", orderNo);
        return R.ok(resultMap);
    }

    /**
     * 预付款
     *
     * @param orderNo
     * @param token
     * @return
     */
    @RequestMapping("/preparePay")
    public R preparePay(@RequestBody String orderNo, @RequestHeader(value = "token") String token) {
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
            } else {
                return R.error(500, "鉴权失败！");
            }
        } else {
            return R.error(500, "无权限访问！");
        }
        return R.ok("下单成功");

//        Order order = orderService.getOne(new QueryWrapper<Order>().eq("orderNo", orderNo));
//
//        System.out.println("totalPrice:" + order.getTotalPrice().movePointRight(2));
//
//        System.out.println("=======");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("appid", weixinpayProperties.getAppid());
//        map.put("mch_id", weixinpayProperties.getMch_id());
//        map.put("openid", openId);
//        // 随机串
//        map.put("nonce_str", StringUtil.getRandomString(32));
//        map.put("device_info", weixinpayProperties.getDevice_info());
//        map.put("notify_url", weixinpayProperties.getNotify_url());
//        map.put("trade_type", "JSAPI"); // 交易类型
//        map.put("out_trade_no", orderNo);
//        map.put("body", "java1234mall商品购买测试");
//        // map.put("total_fee", order.getTotalPrice().movePointRight(2));
//        map.put("total_fee", 1);  // 1分钱测试
//        // map.put("spbill_create_ip", getRemortIP(request)); // 终端IP
//        map.put("spbill_create_ip", "127.0.0.1"); // 终端IP
//        map.put("sign", getSign(map)); // 签名
//        String xml = XmlUtil.genXml(map);
//        System.out.println(xml);
//
//        HttpResponse httpResponse = HttpClientUtil.sendXMLDataByPost(weixinpayProperties.getUrl(), xml);
//        String httpEntityContent = HttpClientUtil.getHttpEntityContent(httpResponse);
//        System.out.println("httpEntityContent:" + httpEntityContent);
//        // 解析返回的xml结果
//        Map resultMap = XmlUtil.doXMLParse(httpEntityContent);
//        System.out.println("resultMap=" + resultMap);
//        if (resultMap.get("result_code").equals("SUCCESS")) {
//            // 封装返回值
//            Map<String, Object> payMap = new HashMap<>();
//            payMap.put("appId", resultMap.get("appid"));
//            payMap.put("nonceStr", StringUtil.getRandomString(32));
//            payMap.put("package", "prepay_id=" + resultMap.get("prepay_id"));
//            payMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//            payMap.put("signType", "MD5");
//            String paySign = getSign(payMap);
//            System.out.println("paySign=" + paySign);
//            System.out.println("==========");
//            payMap.put("paySign", paySign); // 重新签名
//            payMap.put("orderNo", orderNo);
//            System.out.println("payMap=" + payMap);
//
//            return R.ok(payMap);
//        } else {
//            return R.error(500, "系统报错，请联系管理员");
//        }


    }


    /**
     * 微信支付签名算法sign
     */
    private String getSign(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        //获取map中的key转为array
        String[] keyArr = map.keySet().toArray(new String[0]);
        //对array排序
        Arrays.sort(keyArr);
        for (String s : keyArr) {
            if ("sign".equals(s)) {
                continue;
            }
            sb.append(s).append("=").append(map.get(s)).append("&");
        }
        sb.append("key=").append(weixinpayProperties.getKey());
        String sign = string2MD5(sb.toString());
        return sign;
    }

    /***
     * MD5加码 生成32位md5码
     */
    private String string2MD5(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 订单查询  type 值 0 全部订单  1 待付款   2  待收货  3 退款/退货
     *
     * @return
     */
    @RequestMapping("/list")
    public R list(String userCode, Integer type, Integer page, Integer pageSize, @RequestHeader(value = "token") String token) {
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                String openId = claims.getId();

            } else {
                return R.error(500, "鉴权失败！");
            }
        } else {
            return R.error(500, "无权限访问！");
        }
        List<Order> orderList;
        // 定义分页
        Page<Order> pageOrder = new Page<>(page, pageSize);
        Map<String, Object> resultMap = new HashMap<>(8);


        if (type == 0) {
            // 查询全部
            // orderList = orderService.list();
            Page<Order> orderResult = orderService.page(pageOrder, new QueryWrapper<Order>().eq("userId", userCode));
            orderList = orderResult.getRecords();
            resultMap.put("total", orderResult.getTotal());
            resultMap.put("totalPage", +orderResult.getPages());

        } else {
            // 根据状态查询
            // orderList=orderService.list(new QueryWrapper<Order>().eq("status",type));
            Page<Order> orderResult = orderService.page(pageOrder, new QueryWrapper<Order>().eq("status", type).eq("userId", userCode));
            orderList = orderResult.getRecords();
            resultMap.put("total", orderResult.getTotal());
            resultMap.put("totalPage", +orderResult.getPages());
        }
        resultMap.put("page", page);
        resultMap.put("orderList", orderList);
        return R.ok(resultMap);
    }

    @RequestMapping("/cancel")
    public R cancel(String orderNo) {
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("orderNo", orderNo));
        order.setStatus(3);
        orderService.saveOrUpdate(order, new QueryWrapper<Order>().eq("orderNo", orderNo));
        return R.ok();
    }
}
