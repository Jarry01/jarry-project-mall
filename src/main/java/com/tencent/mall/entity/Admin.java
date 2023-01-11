package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 管理员实体
 *
 * @author Jarry
 */
@TableName("t_admin")
@Data
public class Admin {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 新密码
     */
    @TableField(select = false)
    private String newPassword;
}
