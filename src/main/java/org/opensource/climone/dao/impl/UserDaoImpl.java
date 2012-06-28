package org.opensource.climone.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.opensource.climone.dao.UserDao;
import org.opensource.climone.entities.EntityFilter;
import org.opensource.climone.entities.security.User;
import org.opensource.climone.entities.security.UserFilter;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends HibernateDao<User> implements UserDao {

	@Override
	public User getUserByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);

		final String query = "select distinct u from User u inner join fetch u.roles r inner join fetch r.permissions where u.username = :username";

		return findUnique(query, paramMap);
	}

	@Override
	protected DetachedCriteria fillCriteria(EntityFilter<User> entityFilter, DetachedCriteria criteria) {
		UserFilter filter = (UserFilter) entityFilter;

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getName())) {
				criteria.add(Restrictions.or(Restrictions.ilike("firstName", filter.getName(), MatchMode.ANYWHERE),
						Restrictions.ilike("lastName", filter.getName(), MatchMode.ANYWHERE)));
			}
		}

		return criteria;
	}
}
