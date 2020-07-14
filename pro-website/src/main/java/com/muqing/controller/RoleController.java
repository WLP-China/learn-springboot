package com.muqing.controller;

import java.util.List;

import com.google.common.collect.Maps;
import com.muqing.common.page.table.PageTableHandler;
import com.muqing.common.page.table.PageTableHandler.CountHandler;
import com.muqing.common.page.table.PageTableHandler.ListHandler;
import com.muqing.common.page.table.PageTableRequest;
import com.muqing.common.page.table.PageTableResponse;
import com.muqing.dao.RoleDao;
import com.muqing.dto.RoleDTO;
import com.muqing.model.SysRole;
import com.muqing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色相关接口
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDao roleDao;

    /**
     * 保存角色
     *
     * @param roleDTO
     */
//	@LogAnnotation
    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public void saveRole(@RequestBody RoleDTO roleDTO) {
        roleService.saveRole(roleDTO);
    }

    /**
     * 删除角色
     *
     * @param id
     */
//	@LogAnnotation
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    /**
     * 角色列表
     *
     * @param request
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:role:query')")
    public PageTableResponse listRoles(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return roleDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<SysRole> list(PageTableRequest request) {
                List<SysRole> list = roleDao.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    /**
     * 根据id获取角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public SysRole get(@PathVariable Long id) {
        return roleDao.getById(id);
    }

    /**
     * 所有角色
     *
     * @return
     */
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<SysRole> roles() {
        return roleDao.list(Maps.newHashMap(), null, null);
    }

    /**
     * 根据用户id获取拥有的角色
     *
     * @param userId
     * @return
     */
    @GetMapping(params = "userId")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<SysRole> roles(Long userId) {
        return roleDao.listByUserId(userId);
    }
}