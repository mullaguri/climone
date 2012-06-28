package org.opensource.climone.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.opensource.climone.dao.PermissionDao;
import org.opensource.climone.entities.EntityFilter;
import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.PermissionFilter;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends HibernateDao<Permission> implements PermissionDao {

	@Override
	protected DetachedCriteria fillCriteria(EntityFilter<Permission> entityFilter, DetachedCriteria criteria) {
		final PermissionFilter filter = (PermissionFilter) entityFilter;

		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName()));
			}
		}

		return criteria;
	}

}
