package com.tencent.mall.constant;

/**
 * 系统级静态变量
 * @author Jarry
 */
public class SystemConstant {

    /**
     * Token不存在
     */
    public static final int JWT_ERRCODE_NULL = 4000;
    /**
     * Token过期
     */
    public static final int JWT_ERRCODE_EXPIRE = 4001;
    /**
     * 验证不通过
     */
    public static final int JWT_ERRCODE_FAIL = 4002;
    /**
     * JWT 密匙
     */
    public static final String JWT_SECERT = "8677df7fc3a34e26a61c034d5ec8245d";
    /**
     * JWT token有效时间
     */
    public static final long JWT_TTL = 24 * 60 * 60 * 1000;

    public static final String DELETE = "0";
    public static final String EDIT = "1";
    public static final String DEFAULT = "2";
}
