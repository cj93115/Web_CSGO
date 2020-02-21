package com.Web_CSGO.common.util;

import java.math.BigDecimal;

/**
 * @author 1234
 */
public class ParameterUtil {

    public static boolean isNoParameter(Object parameter){
        if (parameter==null) {
            return true;
        }
        if (parameter.toString().trim().equals("")) {
            return true;
        }
        if (parameter.toString().trim().equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 分转成元
     */
    public static String fenZhuanYuan(Integer i){
        return BigDecimal.valueOf(i).divide(new BigDecimal(100)).toString();
    }

}
