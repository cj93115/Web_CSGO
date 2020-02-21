package com.Web_GSGO.common.support;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @author chenlong
 * @version 2018年04月04日 08时54分
 */
public class SecurityCoder {
    private static Byte ADDFLAG = 0;

    static {
        if (ADDFLAG == 0) {
            // 加入BouncyCastleProvider支持
            Security.addProvider(new BouncyCastleProvider());
            ADDFLAG = 1;
        }
    }
}
