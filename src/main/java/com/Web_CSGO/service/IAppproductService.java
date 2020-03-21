package com.Web_CSGO.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.entity.Appproduct;
import java.util.List;
/**
 * (Appproduct)表服务接口
 *
 * @author caojie
 * @since 2020-03-15 16:15:07
 */
public interface IAppproductService extends IService<Appproduct> {

     List<Appproduct>  queryAll(Page page, Appproduct APPProduct);
     
          ResultTip saveOrUpdateData(Appproduct APPProduct);

}