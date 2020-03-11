package com.Web_CSGO.controller.user;

import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.PageUtil;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.entity.UserTest;
import com.Web_CSGO.service.IUserTestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("usertest")
public class UserTestController extends BaseController{
    @Autowired
    private IUserTestService userTestService;


    /**
     * 查询
     * @return
     */
    @GetMapping("getList")
    public @ResponseBody
    JSONObject getList() {
        Page<UserTest> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserTest> list = userTestService.getUsetTestList(page);
        page.setRecords(list);
        map.put("total" ,page.getTotal());
        map.put("rows",list);

        return new JSONObject(map);
    }

    /**
     * 新增
     * @param name
     * @return
     */
    @GetMapping("addUser")
    public @ResponseBody
    JSONObject addUser(@RequestParam("name") String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
             userTestService.addUser(name);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "新增失败");
            return new JSONObject(map);
        }
        map.put("msg", "新增成功");
        return new JSONObject(map);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("deleteUser")
    public @ResponseBody
    JSONObject deleteUser(Integer id) {
        return senMessage(userTestService.removeById(id));
    }

    /**
     * 修改
     * @param userTest
     * @return
     */
    @PostMapping("updateUser")
    public @ResponseBody
    JSONObject deleteUser(UserTest userTest) {
        return senMessage(userTestService.updateById(userTest));
    }

    @PostMapping("editUser")
    public @ResponseBody Object editUser(Integer id){
        UserTest getUser = userTestService.getById(id);
        return JSON.toJSON(getUser);
    }

    /**
     * 返回信息结果
     * @param bo
     * @return
     */
    JSONObject  senMessage(boolean bo){
        Map<String, Object> map = new HashMap<String, Object>();
        if (bo){
            map.put("msg", "操作成功");
            return new JSONObject(map);
        }
        map.put("msg", "操作失败");
        return new JSONObject(map);

    }

}
