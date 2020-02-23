package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.mapper.TAdminMapper;
import com.Web_CSGO.service.IAdminService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName TAdminServiceImpl
 *@Description TODO
 *Author cdl
 *@Date 2020/2/20 10:02
 **/
@Service
public class TAdminServiceImpl extends ServiceImpl<TAdminMapper, AdminUser> implements IAdminService {
    @Override
    public List<AdminUser> getAdminUser(Page page,AdminUser user) {
        return this.baseMapper.getAdminUser(page,user);
    }
}
