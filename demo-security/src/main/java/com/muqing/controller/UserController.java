package com.muqing.controller;

import com.muqing.common.utils.UserUtil;
import com.muqing.dao.UserDao;
import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;
import com.muqing.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关接口
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public SysUser saveUser(@RequestBody UserDTO userDTO) {
        SysUser u = userService.getUser(userDTO.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDTO.getUsername() + "已存在");
        }

        return userService.saveUser(userDTO);
    }

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public SysUser updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    /**
     * 更新头像
     *
     * @param headImgUrl
     */
    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        SysUser user = UserUtil.getLoginUser();
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);

        userService.updateUser(userDto);
    }

    /**
     * 修改密码
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('sys:user:password')")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    /**
     * 获取 当前用户+权限 信息
     *
     * @return
     */
    @GetMapping("/current")
    public SysUser currentUser() {
        return UserUtil.getLoginUser();
    }

    /**
     * 获取当前用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public SysUser user(@PathVariable Long id) {
        return userDao.getById(id);
    }
}
