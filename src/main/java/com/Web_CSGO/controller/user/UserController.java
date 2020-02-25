package com.Web_CSGO.controller.user;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.MD5Util;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.service.IOcInformationsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *@ClassName: UserController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/24 9:26
 **/
@Slf4j
@RestController
@RequestMapping("/user/UserController")
public class UserController  extends BaseController {
    @Autowired
    private IOcInformationsService ocInformationsService;

    @GetMapping("getUserPage")
    public ModelAndView getUserPage(){
       return new ModelAndView("main/getUserList");
    }

    @PostMapping("getUserList")
    public Object getUserList(Integer page, Integer rows,OcInformationsEntity oc){
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
        List<OcInformationsEntity> userList = ocInformationsService.getUserList(objectPage,oc);
        JSONObject returnJson = new JSONObject();
        returnJson.put("rows",userList);//每页条数
        returnJson.put("total",objectPage.getTotal());//分页总条数
        return returnJson;
    }

    @PostMapping("addOrUpUser")
    public Object addOrUpUser(OcInformationsEntity user){
        JSONObject returnJson = new JSONObject();
        List<OcInformationsEntity> users = ocInformationsService.getUserList(new Page(), user);
        if(users.size()>0&&"".equals(user.getInformationId())){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败,用户名存在!");
        }
        user.setPassword(MD5Util.string2MD5(user.getPassword()));
        user.setAccountType("MemberUser");
        user.setState(1);
        try {
            if (ocInformationsService.saveOrUpdate(user)){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
        }
        return returnJson;
    }
    @PostMapping("delUser")
    public Object delUser(String informationId){
        JSONObject returnJson = new JSONObject();
        try {
            if (ocInformationsService.removeById(informationId)){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "删除成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","删除失败!");
        }
        return returnJson;
    }

    @PostMapping("editUser")
    public Object editUser(String informationId){
        OcInformationsEntity getUser = ocInformationsService.getById(informationId);
        getUser.setPassword("");
        return JSON.toJSON(getUser);
    }
}
