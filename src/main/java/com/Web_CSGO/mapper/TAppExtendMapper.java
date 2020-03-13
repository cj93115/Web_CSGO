package com.Web_CSGO.mapper;


import com.Web_CSGO.entity.AppExtend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

@Mapper
public interface TAppExtendMapper extends BaseMapper<AppExtend> {
    public List<Map<Object,String>> getAppExtend(Page page, @Param("extend") AppExtend appExtend);
    List<Map<Object,String>>  queryAllList(Page page,@Param("obj") AppExtend obj);
}
