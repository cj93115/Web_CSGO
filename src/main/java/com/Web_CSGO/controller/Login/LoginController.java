package com.Web_CSGO.controller.Login;

import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.service.ILoginService;
import com.Web_CSGO.service.impl.TLoginServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *@ClassName: LoginController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/20 14:51
 **/
@RestController
@RequestMapping("/LoginController")
public class LoginController extends BaseController {

    @Resource
    ILoginService tLoginServiceImpl;

    @GetMapping("userIndexPage")
    public ModelAndView userIndex(){
        return new ModelAndView("main/userIndex");
    }

    @GetMapping("AdminloginPage")
    public ModelAndView AdminloginPage(){
        return new ModelAndView("main/Adminlogin");
    }
    @GetMapping("adminIndexPage")
    public ModelAndView AdminIndex(HttpServletRequest request,ModelMap mmap){
        HttpSession session = request.getSession();
        AdminUser adminUser = (AdminUser) session.getAttribute("AdminUser");
        mmap.put("AdminName",adminUser.getUser_Name());

        return new ModelAndView("main/index");
    }
    @PostMapping("login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, @RequestBody JSONObject json){
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        Integer userType = (Integer) json.get("userType");
        String verification_code = (String) json.get("verification_code");
        return  tLoginServiceImpl.login(username,password,userType,verification_code,request);
    }


    @GetMapping("removeSession")
    public Object removeSession(HttpServletRequest request){
        String userType = request.getParameter("userType");
        if("1".equals(userType)){
            request.getSession().removeAttribute("AdminUser");
        }else {
            request.getSession().removeAttribute("OcInformationsEntity");
        }
        return setSuccessJSONObject();
    }
}


