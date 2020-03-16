package com.Web_CSGO.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.entity.Appkind;
import java.util.List;
/**
 * (Appkind)表服务接口
 *
 * @author makejava
 * @since 2020-03-15 12:02:22
 */
public interface IAppkindService extends IService<Appkind> {

     List<Appkind>  queryAll(Page page, Appkind APPKind);
     
          ResultTip saveOrUpdateData(Appkind APPKind);

}