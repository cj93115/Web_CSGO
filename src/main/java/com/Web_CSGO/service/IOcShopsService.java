package com.Web_CSGO.service;

import com.Web_CSGO.entity.OcShopsEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-25
 */
public interface IOcShopsService extends IService<OcShopsEntity> {
    List<Map<String, Object>> getOpenBoxList(Page page, Map<String, Object> map);
    Integer del(Integer shopId);

}
