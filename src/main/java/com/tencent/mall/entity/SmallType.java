package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品小类
 * @author Jarry
 */
@TableName("t_smallType")
@Data
public class SmallType implements Serializable {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 大类id
     */
    private Integer bigTypeId;

    /**
     * 所属商品大类
     */
    @TableField(select = false)
    private BigType bigType;

    /**
     * 商品集合
     */
    @TableField(select = false)
    private List<Product> productList;

    public BigType getBigType() {
        return bigType;
    }

    public void setBigType(BigType bigType) {
        this.bigType = bigType;
    }
}
