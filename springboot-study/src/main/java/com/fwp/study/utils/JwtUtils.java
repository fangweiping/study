package com.fwp.study.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //令牌密钥
    private static String secret = "123456";

    public static String createToken(Map<String, String> param, long validTime) {
        JWTCreator.Builder builder = JWT.create();
        //设置payload
        param.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        //设置过期时间
        builder.withExpiresAt(new Date(System.currentTimeMillis() + validTime));
        //生成token
        return builder.sign(Algorithm.HMAC256(secret));
    }

    public static String parseToken(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getPayload();
        } catch (Exception e) {
            //有异常表示解析失败，返回null
            return null;
        }
    }
}
