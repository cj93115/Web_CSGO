package com.Web_CSGO.service;

import com.Web_CSGO.entity.UserTest;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.Map;
import java.util.List;


public interface IUserTestService extends IService<UserTest> {

    List<Map<String,Object>>  getUsetTestList();
    void addUser(String name);


}
