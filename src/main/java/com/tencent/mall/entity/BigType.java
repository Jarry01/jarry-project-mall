package com.tencent.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品大类
 *
 * @author Jarry
 */
@TableName("t_bigType")
@Data
public class BigType implements Serializable {

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
     * 封面图片
     */
    private String image = "default.jpg";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @TableField(select = false)
    private List<SmallType> smallTypeList; // 小类集合

}
