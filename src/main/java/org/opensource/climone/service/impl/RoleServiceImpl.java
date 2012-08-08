package org.opensource.climone.service.impl;

import org.opensource.climone.dao.Dao;
import org.opensource.climone.dao.PermissionDao;
import org.opensource.climone.dao.RoleDao;
import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.PermissionFilter;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;

    @Transactional(readOnly = true)
	public List<Role> getList(Role example) {
		return roleDao.getList(new RoleFilter());
	}

    @Override
    @Transactional(readOnly = true)
	public List<Permission> getAllPermissions() {
		return permissionDao.getList(new PermissionFilter());
	}

    @Override
    protected Dao getServiceDao() {
        return roleDao;
    }
}
