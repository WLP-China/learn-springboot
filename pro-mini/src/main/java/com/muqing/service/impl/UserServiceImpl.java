package com.muqing.service.impl;

import java.util.List;

import com.muqing.dao.UserDao;
import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;
import com.muqing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public SysUser saveUser(UserDTO userDTO) {
        SysUser user = userDTO;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(SysUser.Status.VALID);
        userDao.save(user);
        saveUserRoles(user.getId(), userDTO.getRoleIds());

        log.debug("新增用户{}", user.getUsername());
        return user;
    }

    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userDao.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userDao.saveUserRoles(userId, roleIds);
            }
        }
    }

    @Override
    public SysUser getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        SysUser u = userDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
            throw new IllegalArgumentException("旧密码错误");
        }

        userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));

        log.debug("修改{}的密码", username);
    }

    @Override
    @Transactional
    public SysUser updateUser(UserDTO userDTO) {
        userDao.update(userDTO);
        saveUserRoles(userDTO.getId(), userDTO.getRoleIds());

        return userDTO;
    }
}
