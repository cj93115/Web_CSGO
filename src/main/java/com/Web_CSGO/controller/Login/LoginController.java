package com.Web_CSGO.controller.Login;

import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.service.ILoginService;
import com.Web_CSGO.service.impl.TLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("loginPage")
    public ModelAndView loginPage(){
        return new ModelAndView("main/index");
    }

    @GetMapping("AdminloginPage")
    public ModelAndView AdminloginPage(){
        return new ModelAndView("main/Adminlogin");
    }

    @PostMapping("login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, String username, String password, int userType, String verification_code){
        System.out.println(username);
        return  tLoginServiceImpl.login(username,password,userType,verification_code,request);
    }
}
