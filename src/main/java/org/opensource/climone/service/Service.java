package org.opensource.climone.service;

import org.opensource.climone.entities.EntityFilter;

import java.util.List;

public interface Service<E> {
	String getBeanName();
    public List<E> getList(EntityFilter<E> filter);
    public E findByUid(String uid);
    public void saveEntity(E entity);
    public void deleteEntity(E entity);
}
