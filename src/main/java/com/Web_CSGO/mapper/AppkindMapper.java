package com.Web_CSGO.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.Appkind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**
 * (Appkind)表服务接口
 *
 * @author makejava
 * @since 2020-03-13 16:19:00
 */
@Mapper
public interface AppkindMapper extends BaseMapper<Appkind> {

   List<Appkind>  queryAllList(Page page,@Param("obj") Appkind APPKind);
}