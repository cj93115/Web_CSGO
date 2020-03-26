package com.Web_CSGO.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.entity.Appproduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (Appproduct)表服务接口
 *
 * @author caojie
 * @since 2020-03-15 16:15:07
 */
public interface IAppproductService extends IService<Appproduct> {

     List<Appproduct>  queryAll(Page page, Appproduct APPProduct);
     List<Appproduct>  selectByMap(Map<String,Object> map);
     Double SelectBoxSale(String extendId);
     Double selectExtendCount(String extendId);
          ResultTip saveOrUpdateData(Appproduct APPProduct);

}