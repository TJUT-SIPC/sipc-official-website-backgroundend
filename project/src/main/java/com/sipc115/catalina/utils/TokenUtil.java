package com.sipc115.catalina.utils;

import com.sipc115.catalina.configuration.UserConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenUtil {

    /**
     * 签名秘钥
     */
    public static final String SECRET = "Sipc-Catalina";
    /**
     * 签发地
     */
    public static final String issuer = "sipc115.com";
    /**
     * 过期时间
     */
    public static final long ttlMillis = UserConstants.REDIS_TIME;

    /**
     * 生成Token
     * @param id            编号
     * @param issuer        JWT的签发者
     * @param subject       JWT面向的用户
     * @param ttlMillis     签发时间（有效时间）
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis){

        //签名算法，对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);

        //通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //设置JWT声明
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(nowDate)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //设置过期时间
        if(ttlMillis>=0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //构建JWT并将其序列化为一个紧凑的URL安全字符串
        return builder.compact();
    }

    /**
     * 重写生成Token方法
     * @param id        可传入用户名
     * @param subject
     * @return
     */
    public static String createJwtToken(String id, String subject){
        return createJwtToken(id,issuer,subject,ttlMillis);
    }

    public static String createJwtToken(String id){
        return createJwtToken(id, issuer, "",ttlMillis);
    }


    /**
     * 检验token
     * @param jwt   Token
     * @return
     */
    public static Claims parseJWT(String jwt){
        //若果这行代码不是签名的JWS，抛出异常
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

}
