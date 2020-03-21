package com.Web_CSGO.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.Appextend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (Appextend)表服务接口
 *
 * @author caojie
 * @since 2020-03-15 13:12:50
 */
@Mapper
public interface AppextendMapper extends BaseMapper<Appextend> {

   List<Map<String, Object>>  queryAllList(Page page, @Param("obj") Appextend APPExtend);
   List<Map<String, Object>>  queryAllList(@Param("obj") Appextend APPExtend);
}