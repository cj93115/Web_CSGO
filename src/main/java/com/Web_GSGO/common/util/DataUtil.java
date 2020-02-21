package com.Web_GSGO.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author Lianggs
 * @version 2017年10月13日 11时24分
 */
public abstract class DataUtil {
    private DataUtil() {
    }

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param obj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isEmpty(Object obj) {
        if (obj == null)
            return true;
        if (obj == "")
            return true;
        if (obj instanceof String) {
            if (((String) obj).trim().length() == 0) {
                return true;
            }
        } else if (obj instanceof Collection<?>) {
            if (((Collection<?>) obj).size() == 0) {
                return true;
            }
        } else if (obj instanceof Map<?, ?>) {
            if (((Map<?, ?>) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }
}
