package com.ifun.service;

import com.ifun.mapper.UserMapper;
import com.ifun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    public void insertOrUpdateUser(User user) {
        User dbUser = userMapper.findByAccountId(String.valueOf(user.getAccountId()));
        if (dbUser != null) {
            dbUser.setName(user.getName());
            dbUser.setBio(user.getBio());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setToken(user.getToken());
            dbUser.setGmtModified(System.currentTimeMillis());
            userMapper.updateUser(dbUser);
        } else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertUser(user);
        }
    }
}
