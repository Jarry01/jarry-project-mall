package com.tencent.mall.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.constant.SystemConstant;
import com.tencent.mall.entity.Admin;
import com.tencent.mall.entity.R;
import com.tencent.mall.service.IAdminService;
import com.tencent.mall.util.JwtUtils;
import com.tencent.mall.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员Controller
 *
 * @author Jarry
 */
@RestController
public class AdminController {

    @Resource
    private IAdminService adminService;

    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/adminLogin")
    public R adminLogin(@RequestBody Admin admin) {
        if (admin == null) {
            return R.error();
        }
        if (StringUtil.isEmpty(admin.getUserName())) {
            return R.error("用户名不能为空！");
        }
        if (StringUtil.isEmpty(admin.getPassword())) {
            return R.error("密码不能为空！");
        }
        Admin resultAdmin = adminService.getOne(new QueryWrapper<Admin>().eq("userName", admin.getUserName()));
        if (resultAdmin == null) {
            return R.error("用户名不存在！");
        }
        if (!resultAdmin.getPassword().trim().equals(admin.getPassword())) {
            return R.error("用户名或者密码错误！");
        }
        String token = JwtUtils.createJWT("-1", "admin", SystemConstant.JWT_TTL);
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("token", token);
        return R.ok(resultMap);
    }

    /**
     * 修改密码
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin/modifyPassword")
    public R modifyPassword(@RequestBody Admin admin) {
        if (StringUtil.isEmpty(admin.getUserName())) {
            return R.error("用户名不能为空！");
        }
        if (StringUtil.isEmpty(admin.getNewPassword())) {
            return R.error("新密码不能为空！");
        }
        adminService.update(admin);
        return R.ok();
    }

}