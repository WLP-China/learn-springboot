package com.muqing.service;

import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;

public interface UserService {

    SysUser saveUser(UserDTO userDto);

    SysUser updateUser(UserDTO userDto);

    SysUser getUser(String username);

    void changePassword(String username, String oldPassword, String newPassword);

}
