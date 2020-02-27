package com.Web_CSGO.mapper;

import com.Web_CSGO.entity.OcShopsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-25
 */
@Mapper
@Service
public interface TOcShopsMapper extends BaseMapper<OcShopsEntity> {

    List<Map<String, Object>> getOpenBoxList(Page page,@Param("params") Map<String, Object> map);
    Integer del(@Param("shopId") Integer shopId);
}
