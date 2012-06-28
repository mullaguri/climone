package org.opensource.climone.service;

import java.util.List;

import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService extends Service {

	@Transactional(readOnly = true)
	List<Role> getList(Role example);

	List<Permission> getAllPermissions();

	@Transactional
	void saveRole(Role editedRole);

	@Transactional
	void deleteRole(Role editedRole);

	List<Role> getList(RoleFilter filter);

}
