package com.Web_CSGO.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.UserTest;
import com.Web_CSGO.mapper.UserTestMapper;
import com.Web_CSGO.service.IUserTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (UserTest)表服务实现类
 *
 * @author makejava
 * @since 2020-03-13 10:01:29
 */
@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper,UserTest> implements IUserTestService {
    @Override
    public List<UserTest>  queryAll(Page page, UserTest user_test){

       return baseMapper.queryAllList(page,user_test);
    }
}