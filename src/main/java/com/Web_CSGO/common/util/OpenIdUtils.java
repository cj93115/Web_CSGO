package com.Web_CSGO.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by     IntelliJ IDEA
 *
 * @author :      ShaoXiangDong
 * Date         :       2018/1/19
 * Version      :       1.0
 * Company      :       众美力
 */
public class OpenIdUtils {
    public static JSONObject oauth2GetOpenid(String code,String appid,String appsecret) {

        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = HttpUtils.get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSON.parseObject(data);
        //JSONObject json = JSONObject.fromObject(data);
        //用户的唯一标识（openid）
//        String Openid =String.valueOf(json.get("openid"));
        //System.out.println(Openid);
        return json;
    }
}
