package com.Web_CSGO.common;

import com.alibaba.fastjson.JSONObject;

public class SetResponseJSON {
    public String setSuccessJSONObject(int code,String msg, Object data) {
        JSONObject json = new JSONObject();
        if (data != null) {
            json.put("result", data);
        }else{
            json.put("result", new JSONObject());
        }
        json.put("httpCode", code);
        json.put("msg", msg);
        json.put("timestamp", System.currentTimeMillis());
        return json.toJSONString();
    }
}
