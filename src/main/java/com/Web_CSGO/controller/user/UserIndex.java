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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserIndex
 * @Description: TODO
 * Author: cdl
 * @Date: 2020/2/26 14:12
 **/
@RequestMapping("/user")
@RestController
public class UserIndex {


    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/index");
    }

    @RequestMapping("/csgo")
    public ModelAndView csgo(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/csgo");
    }

    @RequestMapping("/shangcheng")
    public ModelAndView shangcheng(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/shangcheng");
    }

    @GetMapping("/kaixiang")
    public ModelAndView kaixiang(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/kaixiang");
    }

    @RequestMapping("/pkRoom")
    public ModelAndView pkRoom(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/pkRoom");
    }
    @RequestMapping("/tuiguang")
    public ModelAndView tuiguang(HttpServletRequest request,ModelMap modelMap) {
        HttpSession session = request.getSession();
        OcInformationsEntity ifor = (OcInformationsEntity) session.getAttribute("OcInformationsEntity");
        if (!ToolUtil.isEmpty(ifor)) {
            modelMap.put("aUsername", ifor.getUserName());
        }
        return new ModelAndView("/main/user/tuiguang");
    }
}
