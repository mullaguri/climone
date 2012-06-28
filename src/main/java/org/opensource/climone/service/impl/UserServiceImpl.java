package org.opensource.climone.service.impl;

import java.util.List;

import org.opensource.climone.dao.RoleDao;
import org.opensource.climone.dao.UserDao;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.entities.security.User;
import org.opensource.climone.entities.security.UserFilter;
import org.opensource.climone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends AbstractService implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getList(new RoleFilter());
	}

	@Override
	public void saveUser(User user, boolean encodePassword) {
		if (encodePassword) {
			final String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), user.getUid());
			user.setPassword(encodedPassword);
		}
		userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> getList(UserFilter filter) {
		return userDao.getList(filter);
	}
}
