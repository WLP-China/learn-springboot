package com.muqing.service;

import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.TokenDTO;

/**
 * Token管理器
 * 可存储到redis或者数据库,具体可看实现类中是否有@Primary注解
 * <p>
 * 注解@Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
 * https://blog.csdn.net/qq_16055765/article/details/78833260
 */
public interface TokenService {

    TokenDTO saveToken(LoginUserDTO loginUserDTO);

    void refresh(LoginUserDTO loginUserDTO);

    LoginUserDTO getLoginUser(String token);

    boolean deleteToken(String token);
}
