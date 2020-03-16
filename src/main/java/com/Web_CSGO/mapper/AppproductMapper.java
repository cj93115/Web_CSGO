package com.Web_CSGO.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.Appproduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * (Appproduct)表服务接口
 *
 * @author caojie
 * @since 2020-03-15 16:15:08
 */
@Mapper
public interface AppproductMapper extends BaseMapper<Appproduct> {

   List<Appproduct>  queryAllList(Page page,@Param("obj") Appproduct APPProduct);
}