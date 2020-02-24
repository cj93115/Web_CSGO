package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.AppExtend;
import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.service.IAppExtendService;
import com.Web_CSGO.service.IAppKindService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/AppExtend")
@EnableTransactionManagement//事务
public class AppExtendController extends BaseController {
    @Resource
    private IAppExtendService appExtendService;
    @Resource
    private IAppKindService appKindService ;

    @GetMapping("getAppExtend")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getAppExtendList");
    }
    /***
     *
     * @param page
     * @param rows
     * @return
     * 获取管理用户列表
     */
    @PostMapping("getAppExtendList")
    public Object getAdminUserList(Integer page, Integer rows, AppExtend appExtend, String start, String end){
        List<AppExtend> appExtends = new ArrayList<>();
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
            size = 10;
        }
        Page<Object> objectPage = new Page<>(current, size);
        /***********分页部分*************/
        appExtends = appExtendService.getAppExtend(objectPage, appExtend);
       for(int i=0;i<appExtends.size();i++){
           AppExtend appExtend1 = appExtends.get(i);
           AppExtend appExtend2 = setAppKind(appExtend1);
           appExtends.set(i,appExtend2);
       }
        JSONObject returnJson = new JSONObject();
        System.out.println(appExtends);
        returnJson.put("rows",appExtends);//每页条数
        returnJson.put("total",objectPage.getTotal());//分页总条数
        return returnJson;
    }
    @PostMapping("delAppExtend")
    public Object delAppExtend(String extendId){
        JSONObject returnJson = new JSONObject();
        boolean remove = appExtendService.removeById(extendId);
        try{
            if (remove){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "删除成功!");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","删除失败!");
        }
        return returnJson;
    }

    @PostMapping("editGetAppExtend")
    public Object editGetAppExtend(String extendId){
       // System.out.println(extendId);
        AppExtend appExtend = appExtendService.getById(extendId);
        appExtend = setAppKind(appExtend);
        return JSON.toJSON(appExtend);
    }
    public AppExtend setAppKind(AppExtend appExtend){
        QueryWrapper<AppKind> appKindQueryWrapper = new QueryWrapper<>();
        appKindQueryWrapper.eq("Kind_ID",appExtend.getKindId());
        appExtend.setAppKind(appKindService.getOne(appKindQueryWrapper));
        return appExtend;
    }
}
