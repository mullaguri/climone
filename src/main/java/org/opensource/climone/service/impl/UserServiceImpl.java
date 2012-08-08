package org.opensource.climone.service.impl;

import org.opensource.climone.dao.Dao;
import org.opensource.climone.dao.RoleDao;
import org.opensource.climone.dao.UserDao;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.entities.security.User;
import org.opensource.climone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl extends AbstractService<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
    @Transactional(readOnly = true)
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
    @Transactional(readOnly = true)
	public List<Role> getAllRoles() {
		return roleDao.getList(new RoleFilter());
	}

    /**
     * Saves the user without encoding its password
     * @param entity
     */
    @Override
    public void saveEntity(User entity) {
        saveUser(entity, false);
    }

    @Override
    @Transactional
	public void saveUser(User user, boolean encodePassword) {
		if (encodePassword) {
			final String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), user.getUid());
			user.setPassword(encodedPassword);
		}
		userDao.save(user);
	}

    @Override
    protected Dao<User> getServiceDao() {
        return userDao;
    }
}
