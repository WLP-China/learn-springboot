package com.muqing.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.muqing.common.api.CommonResult;
import com.muqing.common.utils.UserUtil;
import com.muqing.dao.PermissionDao;
import com.muqing.dto.LoginUserDTO;
import com.muqing.model.SysPermission;
import com.muqing.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 权限相关接口
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PermissionService permissionService;

    /**
     * 当前登录用户拥有的权限
     *
     * @return
     */
    @GetMapping("/current")
    @ResponseBody
    public CommonResult<List<SysPermission>> permissionsCurrent() {
        LoginUserDTO loginUserDTO = UserUtil.getLoginUser();
        List<SysPermission> list = loginUserDTO.getPermissions();
        final List<SysPermission> permissions = list.stream().filter(l -> l.getType().equals(1))
                .collect(Collectors.toList());

//		setChild(permissions);
//		return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        // 支持多级菜单
        List<SysPermission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });
        return CommonResult.success(firstLevel);
    }

    /**
     * 设置子元素
     *
     * @param p
     * @param permissions
     */
    private void setChild(SysPermission p, List<SysPermission> permissions) {
        List<SysPermission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

//	private void setChild(List<Permission> permissions) {
//		permissions.parallelStream().forEach(per -> {
//			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
//					.collect(Collectors.toList());
//			per.setChild(child);
//		});
//	}

    /**
     * 校验权限,校验当前用户的权限
     *
     * @return
     */
    @GetMapping("/owns")
    public Set<String> ownsPermission() {
        List<SysPermission> permissions = UserUtil.getLoginUser().getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }

        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(SysPermission::getPermission).collect(Collectors.toSet());
    }
//---------------------------------------------------------

//
//    /**
//     * 菜单列表
//     * @return
//     */
//    @GetMapping
//    @ResponseBody
//    @PreAuthorize("hasAuthority('sys:menu:query')")
//    public CommonResult<List<SysPermission>> permissionsList() {
//        List<SysPermission> permissionsAll = permissionDao.listAll();
//
//        List<SysPermission> list = Lists.newArrayList();
//        setPermissionsList(0L, permissionsAll, list);
//
//        return CommonResult.success(list);
//    }
//
//    /**
//     * 菜单列表
//     *
//     * @param pId
//     * @param permissionsAll
//     * @param list
//     */
//    private void setPermissionsList(Long pId, List<SysPermission> permissionsAll, List<SysPermission> list) {
//        for (SysPermission per : permissionsAll) {
//            if (per.getParentId().equals(pId)) {
//                list.add(per);
//                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
//                    setPermissionsList(per.getId(), permissionsAll, list);
//                }
//            }
//        }
//    }
//
//    /**
//     * 所有菜单
//     * @return
//     */
//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('sys:menu:query')")
//    public JSONArray permissionsAll() {
//        List<SysPermission> permissionsAll = permissionDao.listAll();
//        JSONArray array = new JSONArray();
//        setPermissionsTree(0L, permissionsAll, array);
//
//        return array;
//    }
//
//    /**
//     * 一级菜单
//     * @return
//     */
//    @GetMapping("/parents")
//    @PreAuthorize("hasAuthority('sys:menu:query')")
//    public List<SysPermission> parentMenu() {
//        List<SysPermission> parents = permissionDao.listParents();
//
//        return parents;
//    }
//
//    /**
//     * 菜单树
//     *
//     * @param pId
//     * @param permissionsAll
//     * @param array
//     */
//    private void setPermissionsTree(Long pId, List<SysPermission> permissionsAll, JSONArray array) {
//        for (SysPermission per : permissionsAll) {
//            if (per.getParentId().equals(pId)) {
//                String string = JSONObject.toJSONString(per);
//                JSONObject parent = (JSONObject) JSONObject.parse(string);
//                array.add(parent);
//
//                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
//                    JSONArray child = new JSONArray();
//                    parent.put("child", child);
//                    setPermissionsTree(per.getId(), permissionsAll, child);
//                }
//            }
//        }
//    }
//
//    @GetMapping(params = "roleId")
////    @ApiOperation(value = "根据角色id获取权限")
//    @PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
//    public List<SysPermission> listByRoleId(Long roleId) {
//        return permissionDao.listByRoleId(roleId);
//    }
//
//    //    @LogAnnotation
//    @PostMapping
////    @ApiOperation(value = "保存菜单")
//    @PreAuthorize("hasAuthority('sys:menu:add')")
//    public void save(@RequestBody SysPermission permission) {
//        permissionDao.save(permission);
//    }
//
//    @GetMapping("/{id}")
////    @ApiOperation(value = "根据菜单id获取菜单")
//    @PreAuthorize("hasAuthority('sys:menu:query')")
//    public SysPermission get(@PathVariable Long id) {
//        return permissionDao.getById(id);
//    }
//
//    //    @LogAnnotation
//    @PutMapping
////    @ApiOperation(value = "修改菜单")
//    @PreAuthorize("hasAuthority('sys:menu:add')")
//    public void update(@RequestBody SysPermission permission) {
//        permissionService.update(permission);
//    }


//    //    @LogAnnotation
//    @DeleteMapping("/{id}")
////    @ApiOperation(value = "删除菜单")
//    @PreAuthorize("hasAuthority('sys:menu:del')")
//    public void delete(@PathVariable Long id) {
//        permissionService.delete(id);
//    }
}