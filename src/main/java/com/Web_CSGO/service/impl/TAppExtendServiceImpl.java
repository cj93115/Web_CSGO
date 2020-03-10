package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.AppExtend;
import com.Web_CSGO.mapper.TAppExtendMapper;
import com.Web_CSGO.service.IAppExtendService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TAppExtendServiceImpl extends ServiceImpl<TAppExtendMapper, AppExtend> implements IAppExtendService{
    @Override
    public List<Map<Object,String>> getAppExtend(Page page, AppExtend appExtend) {
        return  this.baseMapper.getAppExtend(page,appExtend);
    }
}
