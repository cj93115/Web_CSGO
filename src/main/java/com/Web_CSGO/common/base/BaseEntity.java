package com.Web_CSGO.common.base;

import com.alibaba.fastjson.JSONObject;


public class BaseEntity extends JSONObject {

    private static final long serialVersionUID = 1L;

    public BaseEntity() {
        super();
    }

    public BaseEntity set(String key, Object value, boolean force) {
        if (force || value != null) {
            super.put(key, value);
        }
        return this;
    }

    public BaseEntity set(String key, Object value) {
        return this.set(key, value, true);
    }


    public static BaseEntity err(int errCode) {
        return new BaseEntity().set("errCode", errCode);
    }

    public static BaseEntity err(int errCode, String errMsg) {
        return new BaseEntity().set("errCode", errCode).set("errMsg", errMsg);
    }
}
