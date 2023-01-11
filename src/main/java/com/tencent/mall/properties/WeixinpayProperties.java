package com.tencent.mall.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付配置文件
 *
 * @author Administrator
 */
@Component
@ConfigurationProperties(prefix = "weixinpayconfig")
@Data
public class WeixinpayProperties {

    /**
     * 公众账号ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     *  商户的key【API密匙】
     */
    private String key;

    /**
     * api请求地址
     */
    private String url;

    /**
     * 服务器异步通知页面路径
     */
    private String notify_url;

    /**
     * 服务器同步通知页面路径
     */
    private String return_url;


}
