package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.OcInformationsEntity;
import com.Web_CSGO.mapper.TOcInformationsMapper;
import com.Web_CSGO.service.IOcInformationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-21
 */
@Service
public class TOcInformationsServiceImpl extends ServiceImpl<TOcInformationsMapper, OcInformationsEntity> implements IOcInformationsService {

    @Override
    public List<Map<String, Object>> get() {
        return this.baseMapper.get();
    }
}
