package com.Web_CSGO.service;

import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.entity.Product;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> getProduct(Page page, Product product);
}
