package com.Web_GSGO.common.exception;

import com.Web_GSGO.common.HttpCode;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author Lianggs
 * @version 2017年10月26日 14时37分
 */
public abstract class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(Throwable ex) {
        super(ex);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable ex) {
        super(message, ex);
    }

    public void handler(JSONObject json) {
        json.put("httpCode", getHttpCode().value());
        if (StringUtils.isNotBlank(getMessage())) {
            json.put("msg", getMessage());
        } else {
            json.put("msg", getHttpCode().msg());
        }
        json.put("timestamp", System.currentTimeMillis());
    }

    protected abstract HttpCode getHttpCode();

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
