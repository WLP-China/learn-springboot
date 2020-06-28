package com.muqing.controller;

import com.muqing.common.api.CommonResult;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.common.utils.UserUtil;
import com.muqing.dao.UserDao;
import com.muqing.dto.LoginUserDTO;
import com.muqing.dto.UserDTO;
import com.muqing.model.SysUser;
import com.muqing.service.UserService;
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

    /**
     * 添加用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    public CommonResult<SysUser> saveUser(@RequestBody UserDTO userDTO) {
        SysUser u = userService.getUser(userDTO.getUsername());
        if (u != null) {
//            throw new IllegalArgumentException(userDTO.getUsername() + "已存在");
            return CommonResult.failed("用户名[" + userDTO.getUsername() + "]已存在");
        }

        SysUser user = userService.saveUser(userDTO);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    /**
     * 更新用户信息
     *
     * @param userDTO
     * @return
     */
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:add')")
    public CommonResult<SysUser> updateUser(@RequestBody UserDTO userDTO) {
        SysUser user = userService.updateUser(userDTO);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    /**
     * 更新头像
     *
     * @param headImgUrl
     */
    @PutMapping(params = "headImgUrl")
    @ResponseBody
    public CommonResult updateHeadImgUrl(String headImgUrl) {
        SysUser user = UserUtil.getLoginUser();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setHeadImgUrl(headImgUrl);

        SysUser u = userService.updateUser(userDTO);
        if (u == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    /**
     * 修改密码
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    @PutMapping("/{username}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:password')")
    public CommonResult changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
        return CommonResult.success(null);
    }

    /**
     * 获取 当前用户+权限 信息
     *
     * @return
     */
    @GetMapping("/current")
    @ResponseBody
    public CommonResult<SysUser> currentUser() {
        LoginUserDTO userDTO = UserUtil.getLoginUser();
        if (userDTO == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(userDTO);
    }

    /**
     * 获取当前用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:user:query')")
    public CommonResult<SysUser> user(@PathVariable Long id) {
        SysUser user = userDao.getById(id);
        if (user == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    /**
     * 用户列表
     *
     * @param request
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:query')")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(
                new CountHandler() {
                    @Override
                    public int count(PageTableRequest request) {
                        return userDao.count(request.getParams());
                    }
                },
                new ListHandler() {
                    @Override
                    public List<SysUser> list(PageTableRequest request) {
                        List<SysUser> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
                        return list;
                    }
                }
        ).handle(request);
    }
}