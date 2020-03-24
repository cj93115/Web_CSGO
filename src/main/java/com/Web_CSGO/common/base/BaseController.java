package com.Web_CSGO.common.base;


import com.Web_CSGO.common.Constants;
import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.tips.PageInfoBT;
import com.Web_CSGO.common.enums.CodeEnum;
import com.Web_CSGO.common.exception.BaseException;
import com.Web_CSGO.common.exception.IllegalParameterException;
import com.Web_CSGO.common.util.OpenIdUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.github.pagehelper.PageInfo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lianglifeng
 * @version 2018年11月23日
 */
public abstract class BaseController {



    protected final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * 设置请求分页数据
     */
    protected void startPage(Integer pageNum,Integer pageSize) {

        //如果不传参数为null，则默认查第一页每页10条记录
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }


    /**
     * 设置成功响应代码
     *
     * @return
     */
    protected JSONObject setSuccessJSONObject() {
        return setSuccessJSONObject(HttpCode.SUCCESS);
    }

    /**
     * 设置响应代码
     *
     * @param o
     * @return
     */
    protected JSONObject setSuccessJSONObject(Object o) {
        if (o instanceof HttpCode) {
            return setSuccessJSONObject((HttpCode) o, null);
        } else {
            return setSuccessJSONObject(HttpCode.SUCCESS, o);
        }

    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT(Page<T> page) {
        return new PageInfoBT<T>(page);
    }
    protected <T> PageInfoBT<T> packForBT(Page<T> page,long limit,long current) {
        return new PageInfoBT<T>(page,limit,current);
    }

    protected JSONObject setSuccessJSONObject(HttpCode code, Object data) {
        JSONObject json = new JSONObject();
        if (data != null) {
            json.put("result", data);
        } else {
            json.put("result", new JSONObject());
        }
        json.put("httpCode", code.value());
        json.put("msg", code.msg());
        json.put("timestamp", System.currentTimeMillis());
        return json;
    }


    protected JSONObject setSuccessJSONObject(HttpCode code, Object data, String message) {
        JSONObject json = new JSONObject();
        if (data != null) {
            json.put("rows", data);
        } else {
            json.put("rows", new JSONObject());
        }
        json.put("httpCode", code.value());
        json.put("msg", message);
        json.put("timestamp", System.currentTimeMillis());
        return json;
    }

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        logger.error(Constants.Exception_Head, ex);
        logger.error(ex.getMessage());

        JSONObject json = new JSONObject();
        if (ex instanceof BaseException) {
            ((BaseException) ex).handler(json);
        } else if (ex instanceof IllegalArgumentException) {
            new IllegalParameterException(ex.getMessage()).handler(json);
        } else {
            json = setSuccessJSONObject(HttpCode.INTERNAL_SERVER_ERROR);
        }
        request.setAttribute("msg", json.get("msg"));
        response.setContentType("application/json;charset=UTF-8");
//        byte[] bytes = JSON.toJSONBytes(json);
        byte[] bytes = JSON.toJSONBytes(json, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }

    /**
     * 获取请求的IP地址
     *
     * @param request
     * @return
     */
    protected String getIpFromRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    /**
     * 获取openId
     *
     * @param code
     * @return
     */
//    public String getOpenId(String code){
////        return OpenIdUtils.oauth2GetOpenid(code,MiniProgramConfig.APP_ID,MiniProgramConfig.SECRET_KEY);
////    }
    public JSONObject getOpenId(String code, String appid, String appsecret) {
        return OpenIdUtils.oauth2GetOpenid(code, appid, appsecret);
    }

    /**
     * 从请求头token中获取用户名字
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        //去掉前缀获取token值
        String token = authHeader.substring(6);
        return token;
    }

    public Map setPage(JSONObject object){
        Map<String,Integer> map = new HashMap<>();
        Integer current = object.getInteger("current");
        Integer size = object.getInteger("size");
        Integer total = object.getInteger("total");
        //起始页
        if(null == current || current ==0){
            current=1;
        }
        //每页的大小
        if(null == size){
            size = 10;
        }
        if(null == total){
            total = 0;
        }
        map.put("current",current);
        map.put("size",size);
        return map;

    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
        public Object senJsonResul(CodeEnum codeEnum,Page page){
            Map<String,Object> map=new HashMap<>();
                map.put("msg",codeEnum);
                map.put("rows",page);
            return new JSONObject(map);
    }


}
