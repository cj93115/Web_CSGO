package com.Web_GSGO.controller;

import com.Web_GSGO.common.base.BaseController;
import com.Web_GSGO.entity.AdminUser;
import com.Web_GSGO.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@ClassName testController
 *@Description TODO
 *Author cdl
 *@Date 2020/2/20 10:19
 **/
@RestController
@RequestMapping("/testController")
public class testController extends BaseController {
    @Autowired
    private IAdminService adminService;

    @RequestMapping("getAdminUser")
    public void getAdminUser(){
        List<AdminUser> adminUser = adminService.getAdminUser();
        System.err.print(adminUser);
    }
}