package com.Web_CSGO.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *@ClassName: UserIndex
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/26 14:12
 **/
@RestController
@RequestMapping("/UserIndex")
public class UserIndex {
    @GetMapping("/top")
    public ModelAndView index(){
        return new ModelAndView("/user/top");
    }
    @GetMapping("/index")
    public String top(){
        return "/user/index";
    }
}
