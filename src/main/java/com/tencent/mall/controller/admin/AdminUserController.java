package com.tencent.mall.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.mall.entity.PageBean;
import com.tencent.mall.entity.R;
import com.tencent.mall.entity.WxUserInfo;
import com.tencent.mall.service.IWxUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理端-用户Controller控制器
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Resource
    private IWxUserInfoService wxUserInfoService;

    private final static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @RequestMapping("/list")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<WxUserInfo> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        Page<WxUserInfo> pageResult = wxUserInfoService.page(page, new QueryWrapper<WxUserInfo>().like("nickName", query));
        Map<String, Object> map = new HashMap<>(4);
        map.put("userList", pageResult.getRecords());
        map.put("total", pageResult.getTotal());
        return R.ok(map);
    }

}
