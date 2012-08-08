package org.opensource.climone.service.impl;

import org.opensource.climone.dao.Dao;
import org.opensource.climone.entities.EntityFilter;
import org.opensource.climone.service.Service;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Gian Franco Zabarino
 * Date: 06/08/12
 */
public abstract class AbstractService<E> implements Service<E>, BeanNameAware {

    private String beanName;

    protected abstract Dao<E> getServiceDao();

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Transactional(readOnly = true)
    public List<E> getList(EntityFilter<E> filter) {
        return getServiceDao().getList(filter);
    }

    @Transactional(readOnly = true)
    public E findByUid(String uid) {
        return getServiceDao().getByUid(uid);
    }

    @Transactional
    public void saveEntity(E entity) {
        getServiceDao().save(entity);
    }

    @Transactional
    public void deleteEntity(E entity) {
        getServiceDao().delete(entity);
    }
}
