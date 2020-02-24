package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.AppExtend;
import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.mapper.TAppExtendMapper;
import com.Web_CSGO.mapper.TAppKindMapper;
import com.Web_CSGO.service.IAppExtendService;
import com.Web_CSGO.service.IAppKindService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TAppKindServiceImpl extends ServiceImpl<TAppKindMapper, AppKind> implements IAppKindService {

}
