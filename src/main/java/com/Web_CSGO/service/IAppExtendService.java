package com.Web_CSGO.service;

import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.AppExtend;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IAppExtendService extends IService<AppExtend> {
    List<Map<Object,String>> getAppExtend(Page page, AppExtend appExtend);
    List<Map<Object,String>>  queryAll(Page page, AppExtend APPExtend);


}
