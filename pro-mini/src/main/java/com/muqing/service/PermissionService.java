package com.muqing.service;


import com.muqing.model.SysPermission;

public interface PermissionService {

    void save(SysPermission permission);

    void update(SysPermission permission);

    void delete(Long id);
}
