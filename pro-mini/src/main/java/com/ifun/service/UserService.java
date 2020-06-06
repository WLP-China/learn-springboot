package com.ifun.service;

import com.ifun.dto.UserDTO;
import com.ifun.model.SysUser;

public interface UserService {

    SysUser saveUser(UserDTO userDto);

    SysUser updateUser(UserDTO userDto);

    SysUser getUser(String username);

    void changePassword(String username, String oldPassword, String newPassword);

}
