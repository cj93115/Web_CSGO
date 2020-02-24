package com.Web_CSGO.service;

import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.AppExtend;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAppExtendService extends IService<AppExtend> {
    List<AppExtend> getAppExtend(Page page, AppExtend appExtend);
}
