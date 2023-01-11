package com.tencent.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.mall.constant.SystemConstant;
import com.tencent.mall.entity.Address;
import com.tencent.mall.entity.R;
import com.tencent.mall.service.IAddressService;
import com.tencent.mall.util.JwtUtils;
import com.tencent.mall.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地址信息Controller
 *
 * @author Jarry
 */
@RestController
@RequestMapping("/my/address")
public class AddressController {

    @Resource
    private IAddressService addressService;


    /**
     * 查询地址列表
     * @param userCode
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public R list(String userCode, Integer page, Integer pageSize, @RequestHeader(value = "token") String token) {
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                String openId = claims.getId();

            } else {
                return R.error(500, "鉴权失败！");
            }
        } else {
            return R.error(500, "无权限访问！");
        }
        // 定义分页
        Page<Address> pageAddr = new Page<>(page, pageSize);
        Map<String, Object> resultMap = new HashMap<>(8);
        Page<Address> orderResult = addressService.page(pageAddr, new QueryWrapper<Address>().eq("userId", userCode));
        List<Address> addressList = orderResult.getRecords();
        resultMap.put("total", orderResult.getTotal());
        resultMap.put("totalPage", +orderResult.getPages());
        resultMap.put("page", page);
        resultMap.put("addressList", addressList);
        return R.ok(resultMap);
    }

    @RequestMapping("/save")
    public R save(@RequestBody Address address, @RequestHeader(value = "token") String token) {
        if (StringUtil.isNotEmpty(token)) {
            Claims claims = JwtUtils.validateJWT(token).getClaims();
            if (claims != null) {
                String openId = claims.getId();
                address.setUserId(openId);
                address.setCreateDate(new Date());
            } else {
                return R.error(500, "鉴权失败！");
            }
        } else {
            return R.error(500, "无权限访问！");
        }
        if (CollectionUtils.isEmpty(addressService.list(new QueryWrapper<Address>().eq("userId", address.getUserId())))){
            address.setChecked("0");
        }
        if (null != address.getAddrId()){
            addressService.update(address, new QueryWrapper<Address>().eq("addrId", address.getAddrId()));
        } else {
            addressService.save(address);
        }
        Integer addrId = address.getAddrId();
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("addrId", addrId);
        return R.ok(resultMap);
    }
    @RequestMapping("/detail")
    public R detail(Integer addrId) {
        Address address = addressService.getOne(new QueryWrapper<Address>().eq("addrId", addrId));
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("addrInfo", address);
        return R.ok(resultMap);
    }
    @RequestMapping("/edit")
    public R edit(Integer addrId, String addr, String userName, String phone, String type) {
        if (SystemConstant.DELETE.equals(type)) {
            addressService.remove(new QueryWrapper<Address>().eq("addrId", addrId));
            return R.ok();
        } else if (SystemConstant.DEFAULT.equals(type)) {
            Address oldChecked = addressService.getOne(new QueryWrapper<Address>().eq("checked", "0"));
            if (ObjectUtils.isNull(oldChecked)){
                Address newChecked = addressService.getOne(new QueryWrapper<Address>().eq("addrId", addrId));
                newChecked.setChecked("0");
                addressService.update(newChecked, new QueryWrapper<Address>().eq("addrId", addrId));
                return R.ok();
            }
            if (addrId.equals(oldChecked.getAddrId())){
                return R.ok();
            }
            Address newChecked = addressService.getOne(new QueryWrapper<Address>().eq("addrId", addrId));
            oldChecked.setChecked("1");
            newChecked.setChecked("0");
            addressService.saveOrUpdate(oldChecked, new QueryWrapper<Address>().eq("addrId", oldChecked.getAddrId()));
            addressService.saveOrUpdate(newChecked, new QueryWrapper<Address>().eq("addrId", addrId));
            return R.ok();
        } else {
            Address address = addressService.getOne(new QueryWrapper<Address>().eq("addrId", addrId));
            address.setUserName(userName);
            address.setPhone(phone);
            address.setAddr(addr);
            addressService.saveOrUpdate(address, new QueryWrapper<Address>().eq("addrId", addrId));
            return R.ok();
        }
    }
    @RequestMapping("/default")
    public R defaultAddr(String userCode) {
        Address address = addressService.getOne(new QueryWrapper<Address>().eq("checked", "0").eq("userId", userCode));
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("addrInfo", address);
        return R.ok(resultMap);
    }

    @RequestMapping("/remove")
    public R remove(Integer addrId) {
        addressService.remove(new QueryWrapper<Address>().eq("addrId", addrId));
        return R.ok();
    }
}
