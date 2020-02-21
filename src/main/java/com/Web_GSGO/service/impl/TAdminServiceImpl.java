package com.Web_GSGO.service.impl;

import com.Web_GSGO.entity.AdminUser;
import com.Web_GSGO.mapper.TAdminMapper;
import com.Web_GSGO.service.IAdminService;
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
    public List<AdminUser> getAdminUser() {
        return this.baseMapper.getAdminUser();
    }
}
