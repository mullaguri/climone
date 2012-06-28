package org.opensource.climone.service.impl;

import java.util.List;

import org.opensource.climone.dao.PermissionDao;
import org.opensource.climone.dao.RoleDao;
import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.PermissionFilter;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends AbstractService implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Role> getList(Role example) {
		return roleDao.getList(new RoleFilter());
	}

	@Override
	public List<Permission> getAllPermissions() {
		return permissionDao.getList(new PermissionFilter());
	}

	@Override
	public void saveRole(Role editedRole) {
		roleDao.save(editedRole);
	}

	@Override
	public void deleteRole(Role editedRole) {
		roleDao.delete(editedRole);
	}

	@Override
	public List<Role> getList(RoleFilter filter) {
		return roleDao.getList(filter);
	}

}
