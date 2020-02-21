package com.Web_GSGO.common.util;

import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Lianggs
 * @version 2017年07月05日 16时52分
 */
@PropertySource(value = {"classpath:i18n/messages*.properties",
        "classpath:conf/jpush.properties", "classpath:conf/qiniu.properties",
        "classpath:email.properties"})
public final class Resources {

    public static final String LONG_MSG = "longUser";
    /**
     * 极光推送配置
     */
    public static final ResourceBundle JPUSH = ResourceBundle.getBundle("conf/jpush");

    /**
     * 云通信配置
     */
    public static final ResourceBundle SMS = ResourceBundle.getBundle("conf/sms");

    /**
     * 七牛云配置
     */
    public static final ResourceBundle QINIU = ResourceBundle.getBundle("conf/qiniu");
    /**
     * 国际化信息
     */
    private static final Map<String, ResourceBundle> MESSAGES = new HashMap<>();

//    public static final ResourceBundle EMAIL = ResourceBundle.getBundle("conf/email");

    /**
     * 国际化信息
     */
    public static String getMessage(String key, Object... params) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        ResourceBundle message = MESSAGES.get(locale.getLanguage());
        if (message == null) {
            synchronized (MESSAGES) {
                message = MESSAGES.get(locale.getLanguage());
                if (message == null) {
                    message = ResourceBundle.getBundle("i18n/messages", locale);
                    MESSAGES.put(locale.getLanguage(), message);
                }
            }
        }
        if (params != null && params.length > 0) {
            return String.format(message.getString(key), params);
        }
        return message.getString(key);
    }

    /**
     * 清除国际化信息
     */
    public static void flushMessage() {
        MESSAGES.clear();
    }
}