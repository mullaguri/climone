package org.opensource.climone.security;

import org.opensource.climone.entities.security.User;
import org.opensource.climone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		final User user = userService.getUserByUsername(username);
		if (user == null) {
			return null;
		}
		return new UserDetailsWrapper(user);
	}

}
