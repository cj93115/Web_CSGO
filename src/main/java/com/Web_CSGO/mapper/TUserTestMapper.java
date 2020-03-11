package com.Web_CSGO.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Web_CSGO.entity.UserTest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface TUserTestMapper extends BaseMapper<UserTest> {

    List<UserTest> getUserTestList(Page<UserTest> page);

    void addUser(@Param("name") String name);
}
