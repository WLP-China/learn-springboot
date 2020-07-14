package com.muqing.service.impl;

import com.muqing.common.utils.JwtTokenUtil;
import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.TokenDTO;
import com.muqing.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token存到redis的实现类
 * jwt实现的token
 */
//@Primary
@Service
public class TokenServiceJWTImpl implements TokenService {

    /**
     * token过期秒数
     */
    @Value("${token.expiration}")
    private Integer expiration;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate<String, LoginUserDTO> redisTemplate;

    @Override
    public TokenDTO saveToken(LoginUserDTO loginUserDTO) {
        loginUserDTO.setToken(UUID.randomUUID().toString());
        cacheLoginUser(loginUserDTO);

        String jwtToken = jwtTokenUtil.generateToken(loginUserDTO);

        return new TokenDTO(jwtToken, loginUserDTO.getLoginTime());
    }

    /**
     * 缓存用户信息
     */
    private void cacheLoginUser(LoginUserDTO loginUserDTO) {
        loginUserDTO.setLoginTime(System.currentTimeMillis());
        loginUserDTO.setExpireTime(loginUserDTO.getLoginTime() + expiration * 1000);
        // 根据uuid将loginUser缓存
        redisTemplate.boundValueOps(getTokenKey(loginUserDTO.getToken())).set(loginUserDTO, expiration, TimeUnit.SECONDS);
    }

    @Override
    public void refresh(LoginUserDTO loginUserDTO) {
        //更新缓存的用户信息
        cacheLoginUser(loginUserDTO);
    }

    @Override
    public LoginUserDTO getLoginUser(String jwtToken) {
        String uuid = jwtTokenUtil.getUUIDFromToken(jwtToken);
        if (uuid != null) {
            return redisTemplate
                    .boundValueOps(getTokenKey(uuid))
                    .get();
        }
        return null;
    }

    @Override
    public boolean deleteToken(String jwtToken) {
        String uuid = jwtTokenUtil.getUUIDFromToken(jwtToken);
        if (uuid != null) {
            String key = getTokenKey(uuid);
            LoginUserDTO loginUserDTO = redisTemplate.opsForValue().get(key);
            if (loginUserDTO != null) {
                redisTemplate.delete(key);
                return true;
            }
        }
        return false;
    }

    private String getTokenKey(String uuid) {
        return "tokens:" + uuid;
    }
}