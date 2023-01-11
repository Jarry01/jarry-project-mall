package com.tencent.mall.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.mall.entity.PageBean;
import com.tencent.mall.entity.R;
import com.tencent.mall.entity.SmallType;
import com.tencent.mall.service.ISmallTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端-商品小类Controller控制器
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/admin/smallType")
public class AdminSmallTypeController {

    @Resource
    private ISmallTypeService smallTypeService;

    /**
     * 根据条件分页查询
     *
     * @param pageBean
     * @return
     */
    @RequestMapping("/list")
    public R list(@RequestBody PageBean pageBean) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("name", pageBean.getQuery().trim());
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<SmallType> list = smallTypeService.list(map);
        Long total = smallTypeService.getTotal(map);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("smallTypeList", list);
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    /**
     * 添加或者修改
     *
     * @param smallType
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody SmallType smallType) {
        if (smallType.getId() == null || smallType.getId() == -1) {
            smallTypeService.add(smallType);
        } else {
            smallTypeService.update(smallType);
        }
        return R.ok();
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        smallTypeService.removeById(id);
        return R.ok();
    }

    /**
     * 根据商品大类id，查询所有数据 下拉框用到
     *
     * @return
     */
    @RequestMapping("/listAll/{bigTypeId}")
    public R listAll(@PathVariable(value = "bigTypeId") Integer bigTypeId) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("smallTypeList", smallTypeService.list(new QueryWrapper<SmallType>().eq("bigTypeId", bigTypeId)));
        return R.ok(map);
    }

    @GetMapping("/getBigTypeIdBySmallTypeId/{id}")
    public R getBigTypeIdBySmallTypeId(@PathVariable(value = "id") Integer id) {
        Map<String, Object> map = new HashMap<>(4);
        Integer bigTypeId = smallTypeService.getById(id).getBigTypeId();
        map.put("bigTypeId", bigTypeId);
        return R.ok(map);
    }

}
