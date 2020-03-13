package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.PageUtil;
import com.Web_CSGO.entity.AppExtend;

import com.Web_CSGO.service.IAppExtendService;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/AppExtend")
@EnableTransactionManagement//事务
public class AppExtendController extends BaseController {
    @Resource
    private IAppExtendService appExtendService;


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
        List<Map<Object,String>> appExtends = new ArrayList<>();
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
        appExtends = appExtendService.getAppExtend(objectPage, appExtend);
        JSONObject returnJson = new JSONObject();

        returnJson.put("rows",appExtends);//每页条数
        returnJson.put("total",objectPage.getTotal());//分页总条数
        return returnJson;
    }


    @PostMapping("queryAll")
    @ResponseBody
    public JSONObject queryAll(AppExtend APPExtend) {
        Page<Map<Object,String>> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<Object,String>> list =appExtendService.queryAll(page,APPExtend);
        page.setRecords(list);
        map.put("total" ,page.getTotal());
        map.put("rows",list);

        return new JSONObject(map);
    }

    @PostMapping("delAppExtend")
    public Object delAppExtend(String extendId){
        System.out.println(extendId);
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
//    @PostMapping("editGetAppExtend")
//    public Object editGetAppExtend(String extendId){
//        Map<String,Object> map = new HashMap();
//        AppExtend appExtend = appExtendService.getById(extendId);
//        appExtend = setAppKind(appExtend);
//        map.put("appExtend",appExtend);
//        List<AppKind> appKind = appKindService.getAppKind(new Page(), new AppKind());
//        map.put("appKind",appKind);
//        return JSON.toJSON(map);
//    }
//    @PostMapping("addOrUpAppExtend")
//    public Object addOrUpAppExtend(AppExtend appExtend,String kindName){
//        appExtend.setKind_ID(kindName);
//
//        JSONObject returnJson = new JSONObject();
//        List<Map<Object,String>> appExtends = new ArrayList<>();
//        appExtends = appExtendService.getAppExtend(new Page(), appExtend);
//        if (appExtends.size() > 0&&!appExtend.getExtend_ID().equals(appExtends.get(0).get("Extend_ID"))) {
//            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "", "保存失败,用户名存在!");
//        }
//
//        if("".equals(appExtend.getExtend_ID()) || appExtend.getExtend_ID()==null){
//            String uuid = UUID.randomUUID().toString();
//            appExtend.setExtend_ID(uuid);
//        }
//        try {
//            boolean addOrUp = appExtendService.saveOrUpdate(appExtend);
//
//            if (addOrUp){
//                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
//            }
//        }catch (Exception e) {
//            log.error(e.getMessage());
//            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
//        }
//        return returnJson;
//    }
//    public AppExtend setAppKind(AppExtend appExtend){
//        QueryWrapper<AppKind> appKindQueryWrapper = new QueryWrapper<>();
//        appKindQueryWrapper.eq("Kind_ID",appExtend.getExtend_ID());
//        appExtend.setAppKind(appKindService.getOne(appKindQueryWrapper));
//        return appExtend;
//    }
//
//    @PostMapping("getKind")
//    public Object getkind(){
//        List<Map<Object, String>> appExtend = appExtendService.getAppExtend(new Page(), new AppExtend());
//        return JSON.toJSON(appExtend);
//    }
}
