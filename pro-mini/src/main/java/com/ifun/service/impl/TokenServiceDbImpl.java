package com.ifun.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ifun.dao.TokenDao;
import com.ifun.dto.LoginUserDTO;
import com.ifun.dto.TokenDTO;
import com.ifun.model.Token;
import com.ifun.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * token存到数据库的实现类
 */
@Primary
@Service
public class TokenServiceDbImpl implements TokenService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    /**
     * token过期秒数
     */
    @Value("${token.expiration}")
    private Integer expiration;
    /**
     * 私钥
     */
    @Value("${token.jwtSecret}")
    private String jwtSecret;

    private static Key KEY = null;
    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Autowired
    private TokenDao tokenDao;
//    @Autowired
//    private SysLogService logService;

    @Override
    public TokenDTO saveToken(LoginUserDTO loginUser) {
        loginUser.setToken(UUID.randomUUID().toString());
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expiration * 1000);

        Token token = new Token();
        token.setId(loginUser.getToken());
        token.setCreateTime(new Date());
        token.setUpdateTime(new Date());
        token.setExpireTime(new Date(loginUser.getExpireTime()));
        token.setVal(JSONObject.toJSONString(loginUser));

        tokenDao.save(token);
        // 登陆日志
//        logService.save(loginUser.getId(), "登陆", true, null);

        String jwtToken = createJWTToken(loginUser);

        return new TokenDTO(jwtToken, loginUser.getLoginTime());
    }

    /**
     * 生成jwt
     *
     * @param loginUserDTO
     * @return
     */
    private String createJWTToken(LoginUserDTO loginUserDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY, loginUserDTO.getToken());// 放入一个随机字符串，通过该串可找到登陆用户

        String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance())
                .compact();

        return jwtToken;
    }

    @Override
    public void refresh(LoginUserDTO loginUserDTO) {
        loginUserDTO.setLoginTime(System.currentTimeMillis());
        loginUserDTO.setExpireTime(loginUserDTO.getLoginTime() + expiration * 1000);

        Token token = tokenDao.getById(loginUserDTO.getToken());
        token.setUpdateTime(new Date());
        token.setExpireTime(new Date(loginUserDTO.getExpireTime()));
        token.setVal(JSONObject.toJSONString(loginUserDTO));

        tokenDao.update(token);
    }

    @Override
    public LoginUserDTO getLoginUser(String jwtToken) {
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null) {
            Token token = tokenDao.getById(uuid);
            return toLoginUser(token);
        }
        return null;
    }

    @Override
    public boolean deleteToken(String jwtToken) {
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null) {
            Token token = tokenDao.getById(uuid);
            LoginUserDTO loginUserDTO = toLoginUser(token);
            if (loginUserDTO != null) {
                tokenDao.delete(uuid);
//                logService.save(loginUserDTO.getId(), "退出", true, null);

                return true;
            }
        }
        return false;
    }

    private LoginUserDTO toLoginUser(Token token) {
        if (token == null) {
            return null;
        }

        // 校验是否已过期
        if (token.getExpireTime().getTime() > System.currentTimeMillis()) {
            return JSONObject.parseObject(token.getVal(), LoginUserDTO.class);
        }
        return null;
    }

    private Key getKeyInstance() {
        if (KEY == null) {
            synchronized (TokenServiceDbImpl.class) {
                if (KEY == null) {// 双重锁
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
                    KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
                }
            }
        }
        return KEY;
    }

    private String getUUIDFromJWT(String jwt) {
        if ("null".equals(jwt) || StringUtils.isBlank(jwt)) {
            return null;
        }
        Map<String, Object> jwtClaims = null;
        try {
            jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return MapUtils.getString(jwtClaims, LOGIN_USER_KEY);
        } catch (ExpiredJwtException e) {
            log.error("{}已过期", jwt);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
}