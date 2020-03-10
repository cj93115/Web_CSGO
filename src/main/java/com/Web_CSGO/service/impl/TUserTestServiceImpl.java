package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.Product;
import com.Web_CSGO.entity.UserTest;
import com.Web_CSGO.mapper.TProductMapper;
import com.Web_CSGO.mapper.TUserTestMapper;
import com.Web_CSGO.service.IProductService;
import com.Web_CSGO.service.IUserTestService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TUserTestServiceImpl  extends ServiceImpl<TUserTestMapper, UserTest> implements IUserTestService {


    @Override
    public List<Map<String, Object>> getUsetTestList() {
        return baseMapper.getUserTestList();
    }

    @Override
    public void addUser(String name) {
         baseMapper.addUser(name);
    }

}
