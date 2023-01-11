package com.tencent.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.mall.entity.Admin;


/**
 * 管理员Service接口
 *
 * @author Jarry
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 修改
     *
     * @param admin
     * @return
     */
    public Integer update(Admin admin);

}
