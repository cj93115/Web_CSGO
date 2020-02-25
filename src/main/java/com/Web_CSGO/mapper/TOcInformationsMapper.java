package com.Web_CSGO.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.OcInformationsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-21
 */
@Mapper
@Service
public interface TOcInformationsMapper extends BaseMapper<OcInformationsEntity> {

    List<OcInformationsEntity> getUserList(Page page,@Param("ocInformations") OcInformationsEntity ocInformations);
}
