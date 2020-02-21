package com.Web_CSGO.service;


import com.Web_CSGO.entity.OcInformationsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-21
 */
public interface IOcInformationsService extends IService<OcInformationsEntity> {
    List<Map<String, Object>> get();

}
