package com.Web_CSGO.controller.Login;

import com.Web_CSGO.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *@ClassName: LoginController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/20 14:51
 **/
@Controller
public class LoginController extends BaseController {
    @GetMapping("/")
    public ModelAndView loginPage(){
        return new ModelAndView("main/index");
    }


}
