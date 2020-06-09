package com.muqing.common.utils;

import com.muqing.dto.LoginUserDTO;
import com.muqing.service.impl.TokenServiceDbImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 * 用于生成和解析JWT token
 */
/*
 * JWT token的格式：header.payload.signature
 *
 * header的格式（算法、token的类型）：{"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：{"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_USERID = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static Key KEY = null;

    /**
     * JWT密钥
     */
    @Value("${token.jwtSecret}")
    private String secret;
    /**
     * JWT的过期秒数
     */
    @Value("${token.expiration}")
    private Integer expiration;

    /**
     * 生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
//                .signWith(SignatureAlgorithm.HS512, secret)
                .signWith(SignatureAlgorithm.HS512, getKeyInstance())
                .compact();
    }

    private Key getKeyInstance() {
        if (KEY == null) {
            synchronized (TokenServiceDbImpl.class) {
                if (KEY == null) {// 双重锁
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
                    KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
                }
            }
        }
        return KEY;
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getKeyInstance())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return null;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * `根据用户信息生成token
     */
    public String generateToken(LoginUserDTO userDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, userDTO.getToken());//放入一个随机字符串，通过该字符串可找到登陆用户
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * `从token中获取用户ID
     */
    public String getUUIDFromToken(String token) {
        if (token == null || "null".equals(token) || StringUtils.isBlank(token)) {
            return null;
        }
        try {
            Claims claims = getClaimsFromToken(token);
            return MapUtils.getString(claims, CLAIM_KEY_USERID);
        } catch (ExpiredJwtException e) {
            LOGGER.error("{}已过期", token);
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
        return null;
    }

    /**
     * `验证token是否还有效
     *
     * @param token        客户端传入的token
     * @param loginUserDTO 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, LoginUserDTO loginUserDTO) {
        String uuidFromToken = getUUIDFromToken(token);
        return uuidFromToken.equals(loginUserDTO.getId()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
