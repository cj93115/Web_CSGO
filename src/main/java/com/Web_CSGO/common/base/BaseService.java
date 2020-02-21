package com.Web_CSGO.common.base;



import com.Web_CSGO.common.Constants;
import com.Web_CSGO.common.util.DateUtil;
import com.Web_CSGO.common.util.UUIDUtil;
import org.springframework.cache.annotation.CacheConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Lianggs
 * @version 2017年08月14日 11时09分
 */
public abstract class BaseService<P extends BaseMapper<T>, T extends BaseEntity> {
    protected P mapper;

    /**
     * 添加或修改
     *
     * @param entity
     * @param prefix
     * @return
     */
    public T saveOrUpdate(T entity, String prefix) {
        try {
            if (entity.get("id") == null) {
                if (null != prefix) {
                    Date date = new Date();
                    String time = DateUtil.format(date.getTime(), DateUtil.PATTERN_YYYYMMDDHHMMSS);
                    String id = prefix + time + UUIDUtil.buildRandom(5);
                    entity.set("id", id);
                } else {
                    entity.set("id", UUIDUtil.generateUUID());
                }
//                entity.set("inUse", 0);
                Date d = new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                entity.set("createTime", sdf.format(new Date()));
                mapper.insert(entity);
            } else {
                entity.set("updateTime", new Date());
                mapper.updateByPrimaryKey(entity);
            }
//            RedisUtil.set(getCacheKey(entity.get("id")), entity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return entity;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delete(String id) {
        try {
            T entity = getById(id);
            if (entity != null) {
                int del = mapper.deleteByPrimaryKey(id);
//                String key = getCacheKey(id);
//                RedisUtil.delete(key);
                return del;
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);

        }
        return 0;
    }

//    public void enable(String id, boolean inUse) {
//        try {
//            T record = getById(id);
//            record.set("inUse", inUse);
//            record.set("utime", new Date());
//            mapper.updateByPrimaryKey(record);
//            RedisUtil.set(getCacheKey(id), record);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    public T getById(String id) {
        try {
            String key = getCacheKey(id);
//            T entity = (T) RedisUtil.get(key);
//            if (entity == null) {
              T entity = mapper.selectByPrimaryKey(id);
//                RedisUtil.set(key, entity);
//            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 查询全部 不分页
     *
     * @param params
     * @return
     */
    public List<T> selectAll(Map<String, Object> params) {
        List<T> entity = mapper.selectAll(params);
        return entity;
    }

    /**
     * 根据条件查询ComRate列表(分页)
     */
//    public PageInfo<T> selectAllByPage(Map<String, Object> params) {
//        PageHelper.startPage(PageUtil.getCurPage(params), PageUtil.getCurPageSize(params));
//        List<T> result = mapper.selectAll(params);
//        return new PageInfo<T>(result);
//    }

    private String getCacheKey(Object id) {
        String cacheName = null;
        CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
        if (cacheConfig == null || cacheConfig.cacheNames() == null) {
            cacheName = getClass().getName();
        } else {
            cacheName = cacheConfig.cacheNames()[0];
        }
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }
}
