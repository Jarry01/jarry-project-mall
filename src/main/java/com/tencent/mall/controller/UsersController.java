package com.tencent.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.constant.SystemConstant;
import com.tencent.mall.entity.R;
import com.tencent.mall.entity.WxUserInfo;
import com.tencent.mall.properties.WeixinProperties;
import com.tencent.mall.service.IWxUserInfoService;
import com.tencent.mall.util.HttpClientUtil;
import com.tencent.mall.util.JwtUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信用户Controller
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private WeixinProperties weixinProperties;

    @Resource
    private HttpClientUtil httpClientUtil;

    @Resource
    private IWxUserInfoService wxUserInfoService;

    /**
     * 微信登录
     *
     * @return
     */
    @RequestMapping("/wxlogin")
    public R wxLogin(@RequestBody WxUserInfo wxUserInfo) {
        String jscode2sessionUrl = weixinProperties.getJscode2sessionUrl() + "?appid=" + weixinProperties.getAppid() + "&secret=" + weixinProperties.getSecret() + "&js_code=" + wxUserInfo.getCode() + "&grant_type=authorization_code";
        // 带code请求获取openId
        String result = httpClientUtil.sendHttpGet(jscode2sessionUrl);
        JSONObject jsonObject = JSON.parseObject(result);
        // 获取openId
        String openid = jsonObject.get("openid").toString();
        WxUserInfo resultUserInfo = wxUserInfoService.getOne(new QueryWrapper<WxUserInfo>().eq("openid", openid));
        // 不存在 插入用户
        if (resultUserInfo == null) {
            wxUserInfo.setOpenid(openid);
            wxUserInfo.setRegisterDate(new Date());
            wxUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.save(wxUserInfo);
        } else {
            // 存在 更新用户信息
            resultUserInfo.setNickName(wxUserInfo.getNickName());
            resultUserInfo.setAvatarUrl(wxUserInfo.getAvatarUrl());
            resultUserInfo.setLastLoginDate(new Date());
            wxUserInfoService.updateById(resultUserInfo);
            wxUserInfo.setId(resultUserInfo.getId());
        }
        //把token返回给客户端
        String token = JwtUtils.createJWT(openid, wxUserInfo.getNickName(), SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("token", token);
        resultMap.put("userCode", openid);
        return R.ok(resultMap);

    }
}
