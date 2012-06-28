package org.opensource.climone.service;

import java.util.List;

import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.User;
import org.opensource.climone.entities.security.UserFilter;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends Service {

	User getUserByUsername(final String username);

	List<Role> getAllRoles();

	List<User> getList(UserFilter filter);
	
	@Transactional
	void saveUser(User user, boolean encodePassword);

	@Transactional
	void deleteUser(User user);
}
