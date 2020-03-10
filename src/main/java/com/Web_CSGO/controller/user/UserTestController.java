package com.Web_CSGO.controller.user;

import com.Web_CSGO.entity.UserTest;
import com.Web_CSGO.service.IUserTestService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("usertest")
public class UserTestController {
    @Autowired
    private IUserTestService userTestService;

    @GetMapping("getList")
    public @ResponseBody
    JSONObject getList() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = userTestService.getUsetTestList();
        map.put("msg", "success");
        map.put("data", list);
        return new JSONObject(map);
    }

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

    @GetMapping("deleteUser")
    public @ResponseBody
    JSONObject deleteUser(@RequestParam("id") Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean ifsuccess = userTestService.removeById(id);
        if (ifsuccess) {
            map.put("msg", "删除失败");
            return new JSONObject(map);
        }
        map.put("msg", "删除成功");
        return new JSONObject(map);
    }

    @GetMapping("updateUser")
    public @ResponseBody
    JSONObject deleteUser(UserTest userTest) {
        Map<String, Object> map = new HashMap<String, Object>();



         boolean ifsuccess=  userTestService.updateById(userTest);

      if (ifsuccess){
            map.put("msg", "修改失败");
            return new JSONObject(map);
        }
        map.put("msg", "修改成功");
        return new JSONObject(map);
    }

}
