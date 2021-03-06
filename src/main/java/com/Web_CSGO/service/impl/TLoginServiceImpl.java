package com.Web_CSGO.service.impl;

import com.Web_CSGO.common.util.DateUtil;
import com.Web_CSGO.common.util.MD5Util;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.mapper.TAdminMapper;
import com.Web_CSGO.mapper.TOcInformationsMapper;
import com.Web_CSGO.service.ILoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class TLoginServiceImpl implements ILoginService{
    @Autowired
    TAdminMapper tAdminMapper;
    @Autowired
    TOcInformationsMapper tOcInformationsMapper;
    @Override
    public Map<String, Object> login(String username, String password, int userType, String verification_code, HttpServletRequest request) {
        Map<String,Object> map = new ConcurrentHashMap<>();
        HttpSession session = request.getSession();
        if(userType == 1){
//            String code = (String)session.getAttribute("verification_code");
//            if(code==null){
//                map.put("authenticated",false);
//                map.put("message","验证码错误");
//                return map;
//            }
           // if(code.equals(verification_code)){
                QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("User_Name",username);
                AdminUser adminUser = tAdminMapper.selectOne(queryWrapper);
                if(adminUser==null ){
                    map.put("authenticated",false);
                    map.put("message","用户名或密码错误");
                }
                else{
                    if(adminUser.getUser_Psd().equals(password)){
                        map.put("authenticated",true);
                        map.put("user",adminUser);
                        adminUser.setUser_LoginTime(DateUtil.getTime());
                        tAdminMapper.updateById(adminUser);
                        session.setAttribute("AdminUser",adminUser);
                    }else{
                        map.put("authenticated",false);
                        map.put("message","用户名或密码错误");
                    }
                }
            }/*else {
                map.put("authenticated",false);
                map.put("message","验证码错误");
            }*/
     //   }
        else{
            QueryWrapper<OcInformationsEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("UserName",username);
            OcInformationsEntity ocInformationsEntity = tOcInformationsMapper.selectOne(queryWrapper);
            if(ocInformationsEntity == null){
                map.put("authenticated",false);
                map.put("message","用户名或密码错误");
            }else{
                if(ocInformationsEntity.getPassword().equals(MD5Util.string2MD5(password))){
                    map.put("authenticated",true);
                    map.put("user",ocInformationsEntity);
                    ocInformationsEntity.setSubmitTime(DateUtil.getTime());
                    tOcInformationsMapper.updateById(ocInformationsEntity);
                    ModelMap mmap=new ModelMap();
                    session.setAttribute("OcInformationsEntity",ocInformationsEntity);
                }
                else{
                    map.put("authenticated",false);
                    map.put("message","用户名或密码错误");
                }
            }
        }
        return  map;
    }
}
