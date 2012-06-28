package org.opensource.climone.dao;

import org.opensource.climone.entities.security.User;

public interface UserDao extends Dao<User> {

	User getUserByUsername(String username);

}