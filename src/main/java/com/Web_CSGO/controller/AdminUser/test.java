package com.Web_CSGO.controller.AdminUser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("test")
@Controller
public class test {


    @GetMapping("test2")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/openBoxList");
    }
    @GetMapping("test3")
    public ModelAndView getAdminUserPage3(){
        return new ModelAndView("pk/index");
    }
}
