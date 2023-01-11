package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.Admin;
import com.tencent.mall.mapper.AdminMapper;
import com.tencent.mall.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 管理员Service实现类
 *
 * @author Jarry
 */
@Service("adminService")
public class IAdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;


    @Override
    public Integer update(Admin admin) {
        return adminMapper.update(admin);
    }
}
