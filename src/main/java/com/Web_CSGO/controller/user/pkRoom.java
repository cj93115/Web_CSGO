package com.Web_CSGO.controller.user;

import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.config.WebSocketPkRoom;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: caojie
 * @Date: 2020/3/21 16:30
 */
@RequestMapping("pkRoom")
@RestController
public class pkRoom extends BaseController {


    @Resource
    WebSocketPkRoom webSocketPkRoom;
//public static List<Map<String,Object>> list =new ArrayList<>();
    @GetMapping("getRoom")
    public ModelAndView addOrUpUser(ModelMap model) {
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> list2=new ArrayList<>();
        list2.add("/pksite/images/icon1.jpg");
        list2.add("/pksite/images/icon1.jpg");
        list2.add("/pksite/images/icon1.jpg");

        List<Map<String,Object>> list3=new ArrayList<>();
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("img","/pksite/images/b1.png");
        map3.put("name","小巷子");
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("img","/pksite/images/b1.png");
        map4.put("name","大箱子");
        list3.add(map3);
        list3.add(map4);

        map.put("img",list2);//头像
        map.put("bout",list3.size()+"回合");
        map.put("box",list3); //箱子
        map.put("monye",13);
        map.put("type",1);
        List<Map<String,Object>> list=new ArrayList<>();
        list.add(map);
        model.put("list",list);

       return  new ModelAndView("/main/user/pkRoom");
    }


    @ResponseBody
    @GetMapping("add")
    public void getAdminUserPage(){
        Map<String, Object> map2 = new HashMap<>();

        List<Map<String,Object>> list= WebSocketPkRoom.getRoomList();
        Map<String,Object> map=new HashMap<String,Object>();
        List<String> list2=new ArrayList<>();
        list2.add("/pksite/images/icon1.jpg");

        List<Map<String,Object>> list3=new ArrayList<>();
        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("img","/pksite/images/b1.png");
        map3.put("name","无敌箱子");
        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("img","/pksite/images/b1.png");
        map4.put("name","超大箱子");
        list3.add(map3);
        list3.add(map4);

        map.put("img",list2);//头像
        map.put("bout",list3.size()+"回合");
        map.put("box",list3); //箱子
        map.put("monye",13);
        map.put("type",2);

        list.add(map);
        map2.put("messageType", 4);
        map2.put("textMessage", list);
        map2.put("fromusername", "帶");
        webSocketPkRoom.sendMessageAll(JSON.toJSONString(map2));
    }


    @PostMapping("addRoom")
     public void addRoom(){

      //  WebSocketPkRoom.sendMessageAll(JSON.toJSONString(list));
     }
}
