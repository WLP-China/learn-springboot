package com.muqing.service.impl;

import java.util.List;

import com.muqing.dao.RoleDao;
import com.muqing.dto.RoleDTO;
import com.muqing.model.SysRole;
import com.muqing.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void saveRole(RoleDTO roleDTO) {
        SysRole role = roleDTO;
        List<Long> permissionIds = roleDTO.getPermissionIds();
        permissionIds.remove(0L);

        if (role.getId() != null) {// 修改
            updateRole(role, permissionIds);
        } else {// 新增
            saveRole(role, permissionIds);
        }
    }

    private void saveRole(SysRole sysRole, List<Long> permissionIds) {
        SysRole r = roleDao.getRole(sysRole.getName());
        if (r != null) {
            throw new IllegalArgumentException(sysRole.getName() + "已存在");
        }

        roleDao.save(sysRole);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleDao.saveRolePermission(sysRole.getId(), permissionIds);
        }
        log.debug("新增角色{}", sysRole.getName());
    }

    private void updateRole(SysRole sysRole, List<Long> permissionIds) {
        SysRole r = roleDao.getRole(sysRole.getName());
        if (r != null && r.getId() != sysRole.getId()) {
            throw new IllegalArgumentException(sysRole.getName() + "已存在");
        }

        roleDao.update(sysRole);

        roleDao.deleteRolePermission(sysRole.getId());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleDao.saveRolePermission(sysRole.getId(), permissionIds);
        }
        log.debug("修改角色{}", sysRole.getName());
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleDao.deleteRolePermission(id);
        roleDao.deleteRoleUser(id);
        roleDao.delete(id);

        log.debug("删除角色id:{}", id);
    }
}
