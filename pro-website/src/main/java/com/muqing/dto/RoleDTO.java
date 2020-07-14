package com.muqing.dto;

import com.muqing.model.SysRole;

import java.util.List;


public class RoleDTO extends SysRole {
    private static final long serialVersionUID = 4218495592167610193L;

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
