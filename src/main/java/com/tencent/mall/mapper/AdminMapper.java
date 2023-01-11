package com.tencent.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.mall.entity.Admin;

/**
 * 管理员Mapper接口
 *
 * @author Jarry
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 修改
     *
     * @param admin
     * @return
     */
    public Integer update(Admin admin);

}
