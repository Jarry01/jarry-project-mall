package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.WxUserInfo;
import com.tencent.mall.mapper.WxUserInfoMapper;
import com.tencent.mall.service.IWxUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 微信用户信息Service实现类
 *
 * @author Jarry
 */
@Service("wxUserInfoService")
public class IWxUserInfoServiceImpl extends ServiceImpl<WxUserInfoMapper, WxUserInfo> implements IWxUserInfoService {

    @Resource
    private WxUserInfoMapper wxUserInfoMapper;


}
