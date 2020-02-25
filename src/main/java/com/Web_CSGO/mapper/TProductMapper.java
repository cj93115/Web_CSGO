package com.Web_CSGO.mapper;

import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TProductMapper extends BaseMapper<Product> {
    List<Product> getProduct(Page page, @Param("product") Product product);
}
