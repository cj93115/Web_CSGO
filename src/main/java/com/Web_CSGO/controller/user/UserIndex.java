package com.Web_CSGO.controller.user;

import com.Web_CSGO.common.support.HttpKit;
import com.Web_CSGO.common.util.ToolUtil;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.OcInformationsEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserIndex
 * @Description: TODO
 * Author: cdl
 * @Date: 2020/2/26 14:12
 **/
@RequestMapping("/user")
@Controller
public class UserIndex {


    @RequestMapping("/index")
    public ModelAndView index() {

        ModelMap mmap = new ModelMap();
        HttpSession session = HttpKit.getRequest().getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            mmap.put("aUsername", ifor.getUserName());
            return new ModelAndView("/main/user/index", mmap);
        }
        return new ModelAndView("/main/user/index");
    }

    @RequestMapping("/csgo")
    public ModelAndView csgo() {
        return new ModelAndView("/main/user/csgo");
    }

    @RequestMapping("/shangcheng")
    public ModelAndView shangcheng() {
        return new ModelAndView("/main/user/shangcheng");
    }

    @RequestMapping("/kaixiang")
    public ModelAndView kaixiang() {
        return new ModelAndView("/main/user/kaixiang");
    }

    @RequestMapping("/pkRoom")
    public ModelAndView pkRoom() {
        return new ModelAndView("/main/user/pkRoom");
    }
}
