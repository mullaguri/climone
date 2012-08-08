package org.opensource.climone.service;

import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.User;

import java.util.List;

public interface UserService extends Service<User> {

	User getUserByUsername(final String username);

	List<Role> getAllRoles();

	void saveUser(User user, boolean encodePassword);
}
