package com.tencent.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.mall.entity.WxUserInfo;

/**
 * 微信用户Mapper接口
 *
 * @author Jarry
 */
public interface WxUserInfoMapper extends BaseMapper<WxUserInfo> {

    /**
     * 根据openId查询用户信息
     *
     * @param openid
     * @return
     */
    public WxUserInfo findByOpenId(String openid);

}
