package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户信息实体
 * @author Jarry
 */
@TableName("t_wxUserInfo")
@Data
public class WxUserInfo implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像图片的 URL
     */
    private String avatarUrl;
    /**
     * 注册日期
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date registerDate;
    /**
     * 最后登录日期
     */
    @JsonSerialize(using=CustomDateTimeSerializer.class)
    private Date lastLoginDate;
    /**
     * 微信用户code 前端传来的
     */
    @TableField(select = false,exist = false)
    private String code;


}
