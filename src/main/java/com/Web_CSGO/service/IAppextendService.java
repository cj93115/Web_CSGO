package com.Web_CSGO.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.entity.Appextend;
import java.util.List;
import java.util.Map;

/**
 * (Appextend)表服务接口
 *
 * @author caojie
 * @since 2020-03-15 13:12:47
 */
public interface IAppextendService extends IService<Appextend> {

     List<Map<String, Object>>  queryAll(Page page, Appextend APPExtend);
     List<Map<String, Object>>  queryAll(Appextend APPExtend);
     List<Map<String, Object>>  extendList(Appextend APPExtend);
          ResultTip saveOrUpdateData(Appextend APPExtend);

}