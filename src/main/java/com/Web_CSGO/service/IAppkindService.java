package com.Web_CSGO.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.entity.Appkind;
import java.util.List;
/**
 * (Appkind)表服务接口
 *
 * @author makejava
 * @since 2020-03-13 16:19:00
 */
public interface IAppkindService extends IService<Appkind> {

     List<Appkind>  queryAll(Page page, Appkind APPKind);

     JSONObject saveData(Appkind APPKind);
     JSONObject updateData(Appkind APPKind);
}