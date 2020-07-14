package com.muqing.service;

import com.muqing.dto.RoleDTO;

public interface RoleService {

	void saveRole(RoleDTO roleDTO);

	void deleteRole(Long id);
}
