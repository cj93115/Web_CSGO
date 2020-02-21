package com.Web_CSGO.common.util;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author lianglifeng
 * @date 2018/7/7 09:14
 * @description: 构造jwt及解析jwt的帮助类
 */
public class JwtHelper {


    /**
     * 解析jwt
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();

//            String createDate = claims.get("createDate").toString();
//            Date curretDate = new Date();
            return claims;
        }
        catch(Exception ex){
            System.out.println("token过期！");
//            Assert.ErrorCode("401");
        }

        return null;
    }

    /**
     * 构建jwt
     * @param name        用户名
     * @param userId      用户ID
//     * @param role        用户权限
     * @param audience
     * @param issuer        JWT的签发者
     * @param TTLMillis      设置过期时间
     * @param base64Security
     * @return
     */
    public static String createJWT(String name, String userId,
                                   String audience, String issuer, long TTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                //payload由claim来拼接
                .claim("unique_name", name)
                .claim("userid", userId)
                .claim("createDate",new Date())
                //签发者
                .setIssuer(issuer)
                .setAudience(audience)
                //签名
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }

    /**
     * 获取token中的用户名
     * @param
     * @return
     */
    public static String getUserId(String jsonWebToken, String base64Security){
        Claims claims = parseJWT(jsonWebToken, base64Security);
        return claims.get("userid").toString();
    }

    /**
     * 获取token中的用户名
     * @param
     * @return
     */
    public static String getUserName(String jsonWebToken, String base64Security){
        Claims claims = parseJWT(jsonWebToken, base64Security);
        return claims.get("unique_name").toString();
    }
}
