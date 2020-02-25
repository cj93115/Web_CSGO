package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.entity.Product;
import com.Web_CSGO.mapper.TAppKindMapper;
import com.Web_CSGO.mapper.TProductMapper;
import com.Web_CSGO.service.IAppKindService;
import com.Web_CSGO.service.IProductService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TProductServiceImpl extends ServiceImpl<TProductMapper, Product> implements IProductService {
    @Override
    public List<Product> getProduct(Page page, Product product) {
        return this.baseMapper.getProduct(page,product);
    }
}
