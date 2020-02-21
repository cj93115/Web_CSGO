package com.Web_CSGO.common.base;

import java.util.List;
import java.util.Map;

/**
 * @author Lianggs
 * @version 2017年07月13日 10时55分
 */
public interface BaseMapper<T extends BaseEntity> {
    List<T> selectAll(Map<String, Object> params);

    int deleteByPrimaryKey(String id);

    T selectByPrimaryKey(String id);

    int insert(T record);

    int updateByPrimaryKey(T record);
}
