package com.jimo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * @author jimo
 * @func Jwt相关
 * @date 17-12-12 下午5:28
 */
public class JwtUtil {
    /**
     * 私钥
     */
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";
    /**
     * 过期时间,测试使用20分钟
     */
    final static long TOKEN_EXP = 1000 * 60 * 20;

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                /*过期时间*/
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * @func 检查token, 只要不正确就会抛出异常
     * @author jimo
     * @date 17-12-12 下午6:21
     */
    static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    /**
     * @func token ok返回true
     * @author wangpeng
     * @date 2018/8/27 16:59
     */
    public static boolean isTokenOk(String token) {
        try {
            Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
