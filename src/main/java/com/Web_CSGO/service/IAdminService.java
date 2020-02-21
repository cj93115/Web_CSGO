package com.Web_CSGO.service;


import com.Web_CSGO.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAdminService extends IService<AdminUser> {
    List<AdminUser> getAdminUser();
}
