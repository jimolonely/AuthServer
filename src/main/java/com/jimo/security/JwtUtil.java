package com.jimo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @Date:17-12-12 下午5:28
 * @Author:root
 * @Desc:Jwt相关
 **/
public class JwtUtil {
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥
    final static long TOKEN_EXP = 1000 * 60;//过期时间,测试使用60秒

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * @Date:17-12-12 下午6:21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }
}
