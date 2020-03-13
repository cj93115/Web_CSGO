package com.Web_CSGO.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.UserTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * (UserTest)表服务接口
 *
 * @author makejava
 * @since 2020-03-13 10:01:30
 */
@Mapper
public interface UserTestMapper extends BaseMapper<UserTest> {

    List<UserTest>  queryAllList(Page page,@Param("obj") UserTest obj);
}