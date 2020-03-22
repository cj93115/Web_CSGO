package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.common.enums.CodeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("test")
@RestController
public class test extends BaseController {


    @GetMapping("test2")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/openBoxList");
    }
    @GetMapping("test3")
    public ModelAndView getAdminUserPage3(){
        return new ModelAndView("pk/index");
    }


    @ResponseBody
    @GetMapping("test4")
    public Object getAdminUserPage4(ModelMap modelMap){
        Map map=new  HashMap<String,Object>();
        map.put("test1",11);
        map.put("test2",22);
        map.put("test3",33);
        map.put("test4",44);
        Map map2=new  HashMap<String,Object>();
        map2.put("test1",11);
        map2.put("test2",22);
        map2.put("test3",33);
        map2.put("test4",44);
        Map map3=new  HashMap<String,Object>();
        map3.put("test1",11);
        map3.put("test2",22);
        map3.put("test3",33);
        map3.put("test4",44);
        List<Map<String,Object>> list =new ArrayList<Map<String,Object>>() ;
list.add(map);
list.add(map2);
list.add(map3);
        modelMap.put("list",list);
        return  null;
    }

}
