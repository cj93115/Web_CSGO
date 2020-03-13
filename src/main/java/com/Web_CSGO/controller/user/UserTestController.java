package com.Web_CSGO.controller.user;

import com.Web_CSGO.entity.UserTest;
import com.Web_CSGO.service.IUserTestService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Web_CSGO.common.util.PageUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserTest)表控制层
 *
 * @author makejava
 * @since 2020-03-13 10:12:17
 */
@RestController
@RequestMapping("userTest")
public class UserTestController {
    /**
     * 服务对象
     */
    @Autowired
    private IUserTestService userTestService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserTest selectOne(Integer id) {
        return userTestService.getById(id);
    }
    
    @GetMapping("queryAll")
    @ResponseBody
    public JSONObject queryAll(UserTest user_test) {
     Page<UserTest> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserTest> list =userTestService.queryAll(page,user_test);
        page.setRecords(list);
        map.put("total" ,page.getTotal());
        map.put("rows",list);

        return new JSONObject(map);
    }
    
        //添加
    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(UserTest user_test){
        try {
            userTestService.save(user_test);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@ModelAttribute("editUserTest")UserTest user_test){
        try {
            userTestService.updateById(user_test);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 删除功能,前台要求返回{success:true/false,msg:xxx}
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(Long id){
        try {
           userTestService.removeById(id);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}