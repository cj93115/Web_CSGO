package com.Web_GSGO.common;


import com.Web_GSGO.common.util.IDCardUtil;
import com.Web_GSGO.common.util.PatternUtil;
import com.Web_GSGO.common.util.Resources;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lianggs
 * @version 2017年10月26日 15时29分
 */
public abstract class Assert {
    private static String getMessage(String key, Object... args) {
        return Resources.getMessage(key, args);
    }

    /**
     * (key_IS_NULL)
     */
    public static void isNotNull(Object obj, String key, Object... args) {
        if (obj == null || obj == "") {
            throw new IllegalArgumentException(getMessage(key + "_IS_NULL", args));
        }
    }

    public static void isExist(String object, String key, Object... args) {
        if (StringUtils.isNotEmpty(object)) {
            throw new IllegalArgumentException(getMessage(key + "_IS_EXIST", args));
        }
    }

    public static void isNotExist(String object, String key, Object... args) {
        if (StringUtils.isNotEmpty(object)) {
            throw new IllegalArgumentException(getMessage(key + "_IS_NOT_EXIST", args));
        }
    }

    /**
     * (key_FAIL)
     *
     * @param key
     */
    public static void isFail(String key) {
        throw new IllegalArgumentException(getMessage(key + "_FAIL"));
    }

    /**
     * (key_NOT_NULL)
     *
     * @param object
     * @param key
     * @param args
     */
    public static void isNull(Object object, String key, Object... args) {
        if (object != null) {
            throw new IllegalArgumentException(getMessage(key + "_NOT_NULL", args));
        }
    }

    /**
     * (key_IS_NULL)
     */
    public static void isNotBlank(String s, String key, Object... args) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException(getMessage(key + "_IS_NULL", args));
        }
    }

    /**
     * (key_IS_NULL)
     */
    public static void isInvalid(String key, Object... args) {
        throw new IllegalArgumentException(getMessage(key + "_INVALID", args));
    }

    /**
     * 密码错误信息
     * (key_IS_NULL)
     */
    public static void isERROR(String message) {
        throw new IllegalArgumentException(message);
    }

    /**
     * 身份证
     */
    public static void idCard(String text) {
        if (!IDCardUtil.isIdentity(text)) {
            throw new IllegalArgumentException(getMessage("ID_CARD_ERROR"));
        }
    }


    public static void registFail() {
        throw new IllegalArgumentException(getMessage("REGIST_FAIL"));
    }

    public static void objectError(String object, String errorMsg) {
        if (StringUtils.isNotBlank(object)) {
            throw new IllegalArgumentException(errorMsg + getMessage("OBJECT_ERROR"));
        }
    }

    public static void objectIsNotNull(String object, String errorMsg) {
        if (StringUtils.isNotBlank(object)) {
            throw new IllegalArgumentException(errorMsg + getMessage("OBJECT_IS_NULL"));
        }

    }

    public static void ErrorCode(String code) {
        throw new IllegalArgumentException(code);
    }

    /**
     * 手机号
     */
    public static void mobile(String phone) {
        pattern(phone, PatternUtil.REGEX_MOBILE_NEW, true, "MOBILE");
    }

    /**
     * 邮箱
     */
    public static void email(String text) {
        String regex = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        pattern(text, regex, true, "EMAIL");
    }

    /**
     * 正则表达式
     */
    public static void pattern(String text, String regex, boolean flag, String key) {
        boolean result = false;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            result = matcher.matches();
        } catch (Exception e) {
            result = false;
        }
        if (result != flag) {
            throw new IllegalArgumentException(getMessage(key + "_ILLEGAL"));
        }
    }
}
