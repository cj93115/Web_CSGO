package com.Web_CSGO.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Web_CSGO.entity.UserTest;
import java.util.List;
/**
 * (UserTest)表服务接口
 *
 * @author makejava
 * @since 2020-03-13 10:02:09
 */
public interface IUserTestService extends IService<UserTest> {

    List<UserTest>  queryAll(Page page,UserTest usertest);

}