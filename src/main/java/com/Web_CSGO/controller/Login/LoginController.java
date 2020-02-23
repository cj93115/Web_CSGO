package com.Web_CSGO.controller.Login;

import com.Web_CSGO.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *@ClassName: LoginController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/20 14:51
 **/
@RestController
@RequestMapping("/LoginController")
public class LoginController extends BaseController {
    @GetMapping("loginPage")
    public ModelAndView loginPage(){
        return new ModelAndView("main/index");
    }

    @GetMapping("AdminloginPage")
    public ModelAndView AdminloginPage(){
        return new ModelAndView("main/Adminlogin");
    }



}
