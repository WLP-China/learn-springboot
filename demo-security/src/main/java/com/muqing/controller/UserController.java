package com.muqing.controller;

import com.muqing.common.utils.UserUtil;
import com.muqing.dao.UserDao;
import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;
import com.muqing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public SysUser saveUser(@RequestBody UserDTO userDTO) {
        SysUser u = userService.getUser(userDTO.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDTO.getUsername() + "已存在");
        }

        return userService.saveUser(userDTO);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public SysUser updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        SysUser user = UserUtil.getLoginUser();
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);

        userService.updateUser(userDto);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('sys:user:password')")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('sys:user:query')")
//    public PageTableResponse listUsers(PageTableRequest request) {
//        return new PageTableHandler(
//                new CountHandler() {
//                    @Override
//                    public int count(PageTableRequest request) {
//                        return userDao.count(request.getParams());
//                    }
//                },
//                new ListHandler() {
//                    @Override
//                    public List<SysUser> list(PageTableRequest request) {
//                        List<SysUser> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
//                        return list;
//                    }
//                }
//        ).handle(request);
//    }

    @GetMapping("/current")
    public SysUser currentUser() {
        return UserUtil.getLoginUser();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public SysUser user(@PathVariable Long id) {
        return userDao.getById(id);
    }
}
