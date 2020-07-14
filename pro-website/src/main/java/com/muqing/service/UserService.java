package com.muqing.service;

import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;

public interface UserService {

    SysUser saveUser(UserDTO userDTO);

    SysUser updateUser(UserDTO userDTO);

    SysUser getUser(String username);

    void changePassword(String username, String oldPassword, String newPassword);

}
