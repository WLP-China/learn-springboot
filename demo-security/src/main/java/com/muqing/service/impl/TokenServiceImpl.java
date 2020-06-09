package com.muqing.service.impl;

import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.TokenDTO;
import com.muqing.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token存到redis的实现类
 * 简单uuid实现的token，已作废
 */
@Deprecated
//@Service
public class TokenServiceImpl implements TokenService {

    /**
     * token过期秒数
     */
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;
    @Autowired
    private RedisTemplate<String, LoginUserDTO> redisTemplate;
//    @Autowired
//    private SysLogService logService;

    @Override
    public TokenDTO saveToken(LoginUserDTO loginUserDTO) {
        String token = UUID.randomUUID().toString();

        loginUserDTO.setToken(token);
        cacheLoginUser(loginUserDTO);
        // 登陆日志
//        logService.save(loginUser.getId(), "登陆", true, null);

        return new TokenDTO(token, loginUserDTO.getLoginTime());
    }

    /**
     * 缓存用户信息
     */
    private void cacheLoginUser(LoginUserDTO loginUserDTO) {
        loginUserDTO.setLoginTime(System.currentTimeMillis());
        loginUserDTO.setExpireTime(loginUserDTO.getLoginTime() + expireSeconds * 1000);
        // 缓存
        redisTemplate
                .boundValueOps(getTokenKey(loginUserDTO.getToken()))
                .set(loginUserDTO, expireSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void refresh(LoginUserDTO loginUserDTO) {
        //更新缓存的用户信息
        cacheLoginUser(loginUserDTO);
    }

    @Override
    public LoginUserDTO getLoginUser(String token) {
        return redisTemplate
                .boundValueOps(getTokenKey(token))
                .get();
    }

    @Override
    public boolean deleteToken(String token) {
        String key = getTokenKey(token);
        LoginUserDTO loginUserDTO = redisTemplate.opsForValue().get(key);
        if (loginUserDTO != null) {
            redisTemplate.delete(key);
            // 退出日志
//            logService.save(loginUserDTO.getId(), "退出", true, null);

            return true;
        }
        return false;
    }

    private String getTokenKey(String token) {
        return "tokens:" + token;
    }
}
