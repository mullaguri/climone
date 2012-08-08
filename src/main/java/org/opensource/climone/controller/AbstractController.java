package org.opensource.climone.controller;


import org.opensource.climone.service.Service;
import org.opensource.climone.view.datamodel.ListDataModel;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractController<E> {

    protected abstract AbstractModel<E> getModel();
    protected abstract Service<E> getService();
    protected abstract void bindModelToEntity();
    protected abstract void bindEntityToModel();
    protected abstract Class<E> getEntityClass();

    public void populateNonEntityPropertiesToModel() {

    }

    public void search() {
        List<E> entityList = getService().getList(getModel().getFilter());
        getModel().setList(new ListDataModel<E>(entityList));
        getModel().setPageNumber(1);
    }

    public void prepareEntityRender(String uid, UseCaseMode useCaseMode) {
        if (((useCaseMode.equals(UseCaseMode.EDIT) || useCaseMode.equals(UseCaseMode.CONSULT)) && !StringUtils.hasText(uid)) ||
                (useCaseMode.equals(UseCaseMode.NEW) && StringUtils.hasText(uid)) ||
                useCaseMode.equals(UseCaseMode.SEARCH)) {
            throw new IllegalStateException();
        }
        getModel().setMode(useCaseMode);
        try {
            switch (useCaseMode) {
                case NEW:
                    getModel().setEditedEntity(getEntityClass().newInstance());
                    break;
                case EDIT: case CONSULT:
                    getModel().setEditedEntity(getService().findByUid(uid));
            }
            bindEntityToModel();
        } catch (java.lang.InstantiationException ie) {

        } catch (java.lang.IllegalAccessException eae) {

        }
    }

    public boolean confirmSave(MessageContext messageContext) {
        bindModelToEntity();
        try {
            save();
            return true;
        } catch (ConstraintViolationException e) {
            if (messageContext != null) {
                for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
                    messageContext.addMessage(new MessageBuilder()
                            .error()
                            .source(constraintViolation.getPropertyPath().toString())
                            .defaultText(constraintViolation.getMessage())
                            .build());
                }
                return false;
            } else {
                throw e;
            }
        }
    }

    protected void save() {
        getService().saveEntity(getModel().getEditedEntity());
    }

    public void delete() {
        try {
            getService().deleteEntity(getModel().getEditedEntity());
        } catch (DataIntegrityViolationException ex) {
            // TODO show message on screen, whether using a model variable, or to use facesContext messages
        }
    }

}
