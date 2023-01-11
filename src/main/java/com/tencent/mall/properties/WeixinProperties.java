package com.tencent.mall.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信小程序配置文件
 *
 * @author Jarry
 */
@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class WeixinProperties {

    /**
     * 登录凭证校验请求地址
     */
    private String jscode2sessionUrl;

    /**
     * 小程序 appId
     */
    private String appid;

    /**
     * 小程序 appSecret
     */
    private String secret;


}
