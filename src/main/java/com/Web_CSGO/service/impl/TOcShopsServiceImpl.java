package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.OcShopsEntity;
import com.Web_CSGO.mapper.TOcShopsMapper;
import com.Web_CSGO.service.IOcShopsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-02-25
 */
@Service
public class TOcShopsServiceImpl extends ServiceImpl<TOcShopsMapper, OcShopsEntity> implements IOcShopsService {

    @Override
    public List<Map<String, Object>> getOpenBoxList(Page page, Map<String, Object> map) {
        return this.baseMapper.getOpenBoxList(page,map);
    }

    @Override
    public Integer del(Integer shopId) {
        return this.baseMapper.del(shopId);
    }
}
