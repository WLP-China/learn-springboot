package com.muqing.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muqing.entity.User;
import com.muqing.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * Create by iFun on 2020/08/30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{
}
