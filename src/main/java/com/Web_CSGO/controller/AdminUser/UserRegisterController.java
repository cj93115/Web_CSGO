package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.service.IOcInformationsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *@ClassName: UserRegisterController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/21 15:46
 * 用户注册
 **/
@RestController
@RequestMapping("/UserRegisterController")
public class UserRegisterController extends BaseController {
    @Autowired
    private IOcInformationsService ocInformationsService;

    /**
     * 前端用户登录
     * @param object
     * @return
     */
    @PostMapping("register")
    public Object register(@RequestBody JSONObject object){
        OcInformationsEntity ocInformationsEntity = JSON.toJavaObject(object, OcInformationsEntity.class);
        if("".equals(ocInformationsEntity.getUserName())||ocInformationsEntity.getUserName()==null){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST,"","用户名不能为空！");
        } if("".equals(ocInformationsEntity.getPassword())||ocInformationsEntity.getPassword()==null){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST,"","密码不能为空！");
        }
        boolean save = ocInformationsService.save(ocInformationsEntity);
        if(save){
            return setSuccessJSONObject();
        }
        return setSuccessJSONObject(HttpCode.BAD_REQUEST);
    }
}
