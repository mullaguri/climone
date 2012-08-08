package org.opensource.climone.service;

import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.Role;

import java.util.List;

public interface RoleService extends Service<Role> {

	public List<Role> getList(Role example);

	List<Permission> getAllPermissions();
}
