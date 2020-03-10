package com.Web_CSGO.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Web_CSGO.entity.UserTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface TUserTestMapper extends BaseMapper<UserTest> {

    List<Map<String,Object>> getUserTestList();

    void addUser(@Param("name") String name);
}
