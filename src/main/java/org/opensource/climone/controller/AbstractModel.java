package org.opensource.climone.controller;

import org.opensource.climone.entities.EntityFilter;
import org.opensource.climone.view.datamodel.ListDataModel;

import java.io.Serializable;

public abstract class AbstractModel<E> implements Serializable {

	private static final long serialVersionUID = 8873499141448716342L;

	protected UseCaseMode mode;
	protected Integer pageNumber;
    protected E editedEntity;
    protected EntityFilter<E> filter;
    protected ListDataModel<E> list;
	
	protected String email;
	

	public boolean isNewMode() {
		return UseCaseMode.NEW.equals(mode);
	}

	public boolean isConsultMode() {
		return UseCaseMode.CONSULT.equals(mode);
	}

	public boolean isSearchMode() {
		return UseCaseMode.SEARCH.equals(mode);
	}

	public boolean isEditMode() {
		return UseCaseMode.EDIT.equals(mode);
	}

	public UseCaseMode getMode() {
		return mode;
	}

	public void setMode(UseCaseMode mode) {
		this.mode = mode;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public E getEditedEntity() {
        return editedEntity;
    }

    public void setEditedEntity(E editedEntity) {
        this.editedEntity = editedEntity;
    }

    public EntityFilter<E> getFilter() {
        return filter;
    }

    public void setFilter(EntityFilter<E> filter) {
        this.filter = filter;
    }

    public ListDataModel<E> getList() {
        return list;
    }

    public void setList(ListDataModel<E> list) {
        this.list = list;
    }
}
