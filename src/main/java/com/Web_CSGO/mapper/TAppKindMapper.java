package com.Web_CSGO.mapper;

import com.Web_CSGO.entity.AppExtend;
import com.Web_CSGO.entity.AppKind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TAppKindMapper extends BaseMapper<AppKind> {
    public List<AppKind> getAppKind();
}
