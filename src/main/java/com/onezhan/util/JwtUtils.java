package com.onezhan.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 过期时间：60分钟
    private static final long EXPIRE_TIME = 60*60*1000;
    // 私钥
    private static final String TOKEN_SECRET = "privateKey";

    public static long getExpireTime() {
        return EXPIRE_TIME;
    }
    /*
    * 生成签名，EXPIRE_TIME 后过期
    * 根据
    * */
    public static String sign(Map<String, Object> map) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置Header的信息
            Map<String, Object> header = new HashMap<String, Object>(2);
            header.put("typ", "jwt");
            JWTCreator.Builder builder = JWT.create().
                    withHeader(header).
                    withIssuedAt(new Date()).   // 获取发证时间
                    withExpiresAt(date);        // 设置过期时间
            // 这一段在干嘛？
            // entrySet() 应该是吧map的键值对打包成集合S，然后用forEach迭代
            // entry接收到这个集合S的单个值，也就是map的键值对的内容
            // -> {} 应该是Java中Lambda表达式的形态，将entry以参数的形式传入了Lambda表达式中执行
            // instanceof用于测试变量是否是某一个类, 是这个类就执行这个方法
            // 这样做的目的大概是为了给每个键值对调用准确的withClaim()方法
            map.entrySet().forEach(entry -> {
                if(entry.getValue() instanceof Integer) {
                    builder.withClaim(entry.getKey(), (Integer) entry.getValue());
                }
                else if(entry.getValue() instanceof Long) {
                    builder.withClaim(entry.getKey(), (Long) entry.getValue());
                }
                else if(entry.getValue() instanceof String) {
                    builder.withClaim(entry.getKey(), (String) entry.getValue());
                }
                else if(entry.getValue() instanceof Boolean) {
                    builder.withClaim(entry.getKey(), (Boolean) entry.getValue());
                }
                else if(entry.getValue() instanceof Double) {
                    builder.withClaim(entry.getKey(), (Double) entry.getValue());
                }
                else if(entry.getValue() instanceof Date) {
                    builder.withClaim(entry.getKey(), (Date) entry.getValue());
                }
            });
            return builder.sign(algorithm);
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * **/
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取用户自定义的Claim集合
     **/
    public static Map<String, Claim> getClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        Map<String, Claim> claims = verifier.verify(token).getClaims();
        return claims;
    }

    /**
     * 获取过期时间
     * **/
    public static Date getExpiresAt(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return JWT.require(algorithm).build().verify(token).getExpiresAt();
    }

    /**
     * 获取发布时间
     * **/
    public static Date getIssuedAt(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return JWT.require(algorithm).build().verify(token).getIssuedAt();
    }

    /**
     * 判断是否过期
     * **/
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiresAt(token);
            return expiration.before(new Date());
        }
        catch(Exception e) {
            return true;
        }
    }
}
