package com.tencent.mall.controller;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.service.IOrderService;
import com.tencent.mall.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tencent.mall.entity.Order;
import com.tencent.mall.properties.WeixinpayProperties;

/**
 * 微信支付Controller
 *
 * @author Jarry
 */
@Controller
@RequestMapping("/weixinpay")
public class WeixinpayController {

    @Resource
    private WeixinpayProperties weixinpayProperties;

    @Resource
    private IOrderService orderService;

    private final static Logger logger = LoggerFactory.getLogger(WeixinpayController.class);


    /**
     * 微信支付服务器异步通知
     *
     * @param request
     * @throws Exception
     */
    @RequestMapping("/notifyUrl")
    public synchronized void notifyUrl(HttpServletRequest request) throws Exception {
        logger.info("notifyUrl");
        //读取参数
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        logger.info("sb:" + sb.toString());
        //解析xml成map
        Map<String, String> m = new HashMap<>();
        m = XmlUtil.doXMLParse(sb.toString());

        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        assert m != null;
        for (String parameter : m.keySet()) {
            String parameterValue = m.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            logger.info("p:" + parameter + ",v:" + v);
            packageParams.put(parameter, v);
        }

        // 微信支付的API密钥
        String key = weixinpayProperties.getKey();
        String out_trade_no = (String) packageParams.get("out_trade_no");


        if (isTenpaySign("UTF-8", packageParams, key)) {
            // 验证通过
            if ("SUCCESS".equals(packageParams.get("result_code"))) {
                Order order = orderService.getOne(new QueryWrapper<Order>().eq("orderNo", out_trade_no));

                if (order != null && order.getStatus() == 1) {
                    order.setPayDate(new Date());
                    // 设置支付状态已经支付
                    order.setStatus(2);
                    // orderService.save(order);
                    orderService.saveOrUpdate(order);

                    logger.info(out_trade_no + "：微信服务器异步修改订单状态成功！");

                }
            }
        } else {
            logger.info(out_trade_no + "：微信服务器异步验证失败！");
        }


    }


    /**
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     *
     * @return boolean
     */
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuilder sb = new StringBuilder();
        Set es = packageParams.entrySet();
        for (Object e : es) {
            Map.Entry entry = (Map.Entry) e;
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        sb.append("key=").append(API_KEY);

        //算出摘要
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

        return tenpaySign.equals(mysign);
    }

}
