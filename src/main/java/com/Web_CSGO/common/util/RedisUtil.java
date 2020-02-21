package com.Web_CSGO.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static final Integer time = 300;

    private static RedisTemplate redisTemplate;

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置过期时间，秒
     *
     * @param key
     * @param expireTime
     * @return
     */
    public static Boolean expire(String key, Integer expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public static void set(String key, Serializable value, Integer time) {
        redisTemplate.opsForValue().set(key, value);
        expire(key, time);
    }

    public static void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
        expire(key, time);
    }

    /**
     * 添加 set 元素
     *
     * @return 成功添加数量
     */
    public static Long addSet(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 删除一个或多个集合中的指定值
     *
     * @return 成功删除数量
     */
    public static Long removeSetValues(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 随机获取指定数量的元素,去重(同一个元素只能选择两一次)
     */
    public static Set<Object> randomSetDistinct(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    public static void setHash(Object key1, Object key2, Serializable value) {
        redisTemplate.opsForHash().put(key1, key2, value);
    }

    public static void setHash(Object key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public static Object getHash(Object key1, Object key2) {
        return redisTemplate.opsForHash().get(key1, key2);
    }

    public static Object getHash(Object key, Collection hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    public static Long deleteHash(Object key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public static Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


}
