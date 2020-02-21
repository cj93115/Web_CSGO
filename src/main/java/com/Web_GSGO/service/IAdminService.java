package com.Web_GSGO.service;


import com.Web_GSGO.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAdminService extends IService<AdminUser> {
    List<AdminUser> getAdminUser();
}
