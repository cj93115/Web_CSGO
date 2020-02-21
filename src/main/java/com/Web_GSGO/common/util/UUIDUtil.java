package com.Web_GSGO.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Lianggs
 * @version 2017年08月14日 11时26分
 */
public class UUIDUtil {

    //用于生成验证码的字符
    private static char[] codeChar = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * 生成唯一的
     *
     * @return
     */
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    //取出一个固定长度的字符加数组的组合随机字符串
    public static String getUUIDBylength(int length) {
        Random random = new Random();
        String str = String.valueOf(codeChar[random.nextInt(codeChar.length)]);
        return str;
    }

    public static void main(String[] args) {
        System.out.println(buildRandom(5));
    }
}
