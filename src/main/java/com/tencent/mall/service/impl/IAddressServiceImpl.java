package com.tencent.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.mall.entity.Address;
import com.tencent.mall.mapper.AddressMapper;
import com.tencent.mall.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户地址信息Service实现类
 *
 * @author Jarry
 */
@Service("addressService")
public class IAddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Resource
    private AddressMapper addressMapper;


}
