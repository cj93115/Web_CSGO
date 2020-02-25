package com.Web_CSGO.controller.openBox;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.entity.OcShopsEntity;
import com.Web_CSGO.service.IOcInformationsService;
import com.Web_CSGO.service.IOcShopsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @ClassName: OpenBoxController
 * @Description: TODO
 * Author: cdl
 * @Date: 2020/2/25 9:35
 **/
@Slf4j
@RestController
@RequestMapping("/OpenBoxController")
public class OpenBoxController extends BaseController {

    @Autowired
    private IOcShopsService ocShopsService;

    @Autowired
    private IOcInformationsService ocInformationsService;

    @GetMapping("getOpenBox")
    public ModelAndView getOpenBoxPage() {
        return new ModelAndView("main/openBoxList");
    }

    /***
     *
     * @author: Chen_De_lin
     * @date: 2020/2/25 9:38
     * @Description: 箱子管理列表
     **/
    @ResponseBody
    @PostMapping("getOpenBoxList")
    public Object getOpenBoxList(HttpServletRequest request, Integer page, Integer rows) {
        /***********分页部分*************/
        JSONObject object = new JSONObject();
        object.put("current", page);
        object.put("size", rows);
        Integer current = (Integer) setPage(object).get("current");
        if (null == current) {
            current = 0;
        }
        //每页的大小
        Integer size = (Integer) setPage(object).get("size");
        if (null == size) {
            size = 10;
        }
        Page<Object> objectPage = new Page<>(current, size);
        /***********分页部分*************/
        String name = request.getParameter("name");
        String ShopTel = request.getParameter("ShopTel");
        Map<String, Object> map = new HashMap<>();
        if (!"".equals(name)) {
            map.put("name", name);
        }
        if (!"".equals(ShopTel)) {
            map.put("ShopTel", ShopTel);
        }
        List<Map<String, Object>> openBoxList = ocShopsService.getOpenBoxList(objectPage, map);
        JSONObject returnJson = new JSONObject();
        returnJson.put("rows", openBoxList);//每页条数
        returnJson.put("total", objectPage.getTotal());//分页总条数
        return returnJson;
    }

    @PostMapping("getInformation")
    public Object getInformation() {
        List<Map<String, Object>> userList = ocInformationsService.listMaps();
        List<Map<String, Object>> list = new LinkedList<>();
        for (int i=0;i<userList.size();i++){
            HashMap<String, Object> obj = new HashMap<>();
            if(!"null".equals(userList.get(i).get("UserName"))){
                obj.put("id",userList.get(i).get("InformationId"));
                obj.put("text",userList.get(i).get("UserName"));
                list.add(obj);
            }
        }
        return  list;
    }

    @PostMapping("addOrUpOpenBox")
    public Object addOrUpOpenBox(OcShopsEntity ocShopsEntity) {
        JSONObject returnJson = new JSONObject();
        QueryWrapper<OcShopsEntity> ocShopsQuery = new QueryWrapper<>();
        ocShopsQuery.eq("ShopName",ocShopsEntity.getShopName());
        List<Map<String, Object>> list = ocShopsService.listMaps(ocShopsQuery);
        if(list.size()>0){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败,用回名存在!");
        }
        try {
            boolean addOrUp = ocShopsService.saveOrUpdate(ocShopsEntity);
            if (addOrUp){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
        }
        return returnJson;
    }
}
