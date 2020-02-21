package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.service.IAdminService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.media.MediaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@ClassName testController
 *@Description TODO
 *Author cdl
 *@Date 2020/2/20 10:19
 **/
@RestController
@RequestMapping("/AdminUser")
public class AdminUserController extends BaseController {
    @Autowired
    private IAdminService adminService;


    @GetMapping("getAdminUser")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getAdminUserList");
    }

    /***
     *
     * @param page
     * @param rows
     * @param pageSize
     * @return
     * 获取管理用户列表
     */
    @PostMapping("getAdminUserList")
    public Object getAdminUserList(Integer page, Integer rows,Integer pageSize){
        List<AdminUser> adminUser = new ArrayList<>();
        /***********分页部分*************/
        JSONObject object = new JSONObject();
        object.put("current",page);
        object.put("pageSize",rows);
        Integer current = (Integer)setPage(object).get("current");
        if (null == current) {
            current = 0;
        }
        //每页的大小
        Integer size = (Integer)setPage(object).get("pageSize");
        if (null == size) {
            size = 5;
        }
        /***********分页部分*************/

        adminUser = adminService.getAdminUser(new Page<>(current, size));
        System.err.println(JSONObject.toJSON(adminUser));
        JSONObject returnJson = new JSONObject();
        returnJson.put("total",adminUser.size());//分页总条数
        returnJson.put("rows",adminUser);//每页条数
        return returnJson;
    }
}
