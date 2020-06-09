package com.muqing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.muqing.common.utils.JwtTokenUtil;
import com.muqing.dao.TokenDao;
import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.TokenDTO;
import com.muqing.model.Token;
import com.muqing.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * token存到数据库的实现类
 */
//@Primary
@Service
public class TokenServiceDbImpl implements TokenService {

    /**
     * token过期秒数
     */
    @Value("${token.expiration}")
    private Integer expiration;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenDao tokenDao;

    @Override
    public TokenDTO saveToken(LoginUserDTO loginUserDTO) {
        loginUserDTO.setToken(UUID.randomUUID().toString());
        loginUserDTO.setLoginTime(System.currentTimeMillis());
        loginUserDTO.setExpireTime(loginUserDTO.getLoginTime() + expiration * 1000);

        Token token = new Token();
        token.setId(loginUserDTO.getToken());
        token.setCreateTime(new Date());
        token.setUpdateTime(new Date());
        token.setExpireTime(new Date(loginUserDTO.getExpireTime()));
        token.setVal(JSONObject.toJSONString(loginUserDTO));

        tokenDao.save(token);

        String jwtToken = jwtTokenUtil.generateToken(loginUserDTO);

        return new TokenDTO(jwtToken, loginUserDTO.getLoginTime());
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
        String uuid = jwtTokenUtil.getUUIDFromToken(jwtToken);
        if (uuid != null) {
            Token token = tokenDao.getById(uuid);
            return tokenToLoginUserDTO(token);
        }
        return null;
    }

    @Override
    public boolean deleteToken(String jwtToken) {
        String uuid = jwtTokenUtil.getUUIDFromToken(jwtToken);
        if (uuid != null) {
            Token token = tokenDao.getById(uuid);
            LoginUserDTO loginUserDTO = tokenToLoginUserDTO(token);
            if (loginUserDTO != null) {
                tokenDao.delete(uuid);
                return true;
            }
        }
        return false;
    }

    private LoginUserDTO tokenToLoginUserDTO(Token token) {
        if (token == null) {
            return null;
        }

        // 校验是否已过期
        if (token.getExpireTime().getTime() > System.currentTimeMillis()) {
            return JSONObject.parseObject(token.getVal(), LoginUserDTO.class);
        }
        return null;
    }
}