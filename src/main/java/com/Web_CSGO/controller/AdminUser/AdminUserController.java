package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.service.IAdminService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.media.MediaView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *@ClassName testController
 *@Description TODO
 *Author cdl
 *@Date 2020/2/20 10:19
 **/
@Slf4j
@RestController
@RequestMapping("/AdminUser")
@EnableTransactionManagement//事务
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
     * @return
     * 获取管理用户列表
     */
    @PostMapping("getAdminUserList")
    public Object getAdminUserList(Integer page, Integer rows,AdminUser user,String start,String end){
        List<AdminUser> adminUser = new ArrayList<>();
        /***********分页部分*************/
        JSONObject object = new JSONObject();
        object.put("current",page);
        object.put("size",rows);
        Integer current = (Integer)setPage(object).get("current");
        if (null == current) {
            current = 0;
        }
        //每页的大小
        Integer size = (Integer)setPage(object).get("size");
        if (null == size) {
            size = 10;
        }
        Page<Object> objectPage = new Page<>(current, size);
        /***********分页部分*************/
        adminUser = adminService.getAdminUser(objectPage,user);
        JSONObject returnJson = new JSONObject();
        returnJson.put("rows",adminUser);//每页条数
        returnJson.put("total",objectPage.getTotal());//分页总条数
        return returnJson;
    }

    @PostMapping("addOrUpAdminUser")
    public Object addOrUpAdminUser(AdminUser adminUser){
        JSONObject returnJson = new JSONObject();
        List<AdminUser> users = new ArrayList<>();
        users = adminService.getAdminUser(new Page(),adminUser);
        if(users.size()>0){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败,用回名存在!");
        }

        if("".equals(adminUser.getUser_ID())){
            String uuid = UUID.randomUUID().toString();
            adminUser.setUser_ID(uuid);
        }
        try {
            boolean addOrUp = adminService.saveOrUpdate(adminUser);
            if (addOrUp){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
        }
        return returnJson;
    }

    @PostMapping("delAdminUser")
    public Object delAdminUser(String userId){
        JSONObject returnJson = new JSONObject();
        try {
            boolean remove = adminService.removeById(userId);
            if (remove){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "删除成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","删除失败!");
        }
        return returnJson;
    }
    @PostMapping("editGetAdminUser")
    public Object editGetAdminUser(String userId){
        AdminUser adminUser = adminService.getById(userId);
        return JSON.toJSON(adminUser);
    }
}
