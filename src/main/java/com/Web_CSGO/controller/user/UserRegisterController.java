package com.Web_CSGO.controller.user;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.MD5Util;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.service.IOcInformationsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 前端用户注册
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
        ocInformationsEntity.setPassword(MD5Util.string2MD5(ocInformationsEntity.getPassword()));
        QueryWrapper<OcInformationsEntity> on = new QueryWrapper<>();
        on.lambda().eq(OcInformationsEntity::getUserName, ocInformationsEntity.getUserName());
        OcInformationsEntity one = ocInformationsService.getOne(on);
        if(one!=null){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败,用户存在!");
        }
        boolean save = ocInformationsService.save(ocInformationsEntity);
        if(save){
            return setSuccessJSONObject();
        }
        return setSuccessJSONObject(HttpCode.BAD_REQUEST);
    }
}
