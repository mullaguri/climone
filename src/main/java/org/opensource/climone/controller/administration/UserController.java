package org.opensource.climone.controller.administration;

import org.opensource.climone.controller.AbstractController;
import org.opensource.climone.controller.AbstractModel;
import org.opensource.climone.controller.UseCaseMode;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.User;
import org.opensource.climone.entities.security.UserFilter;
import org.opensource.climone.service.MailService;
import org.opensource.climone.service.Service;
import org.opensource.climone.service.UserService;
import org.opensource.climone.view.datamodel.DefaultSelectionDataModelListener;
import org.opensource.climone.view.datamodel.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Controller("userController")
public class UserController extends AbstractController<User> {

	@Autowired
	private UserService service;

	@Autowired
	private UserModel model;
	
	@Autowired
	private MailService<User> mailConfirmationService;

	public void init() {
		model.setFilter(new UserFilter());
		model.setMode(UseCaseMode.SEARCH);
	}

    @Override
    protected void bindModelToEntity() {
        User editedUser = model.getEditedEntity();
        editedUser.setFirstName(model.getFirstName());
        editedUser.setLastName(model.getLastName());
        editedUser.setEmail(model.getEmail());
        editedUser.setUsername(model.getUsername());
        if (model.isNewMode()) {
            editedUser.setPassword(model.getPassword());
        }

        editedUser.getRoles().clear();
        editedUser.getRoles().addAll(
                model.getRolesSelectionListener().getSelectedElements());
    }

    @Override
    protected void bindEntityToModel() {
        User editedUser = model.getEditedEntity();

        model.setFirstName(editedUser.getFirstName());
        model.setLastName(editedUser.getLastName());
        model.setEmail(editedUser.getEmail());
        model.setUsername(editedUser.getUsername());
        model.setPassword(editedUser.getPassword());
        DefaultSelectionDataModelListener<Role> selectionListener = new DefaultSelectionDataModelListener<Role>(
                new HashSet<Role>(editedUser.getRoles()));
        SelectableDataModel<Role> permissionDataModel = new SelectableDataModel<Role>(
                service.getAllRoles(), selectionListener);
        model.setRolesDataModel(permissionDataModel);
        model.setRolesSelectionListener(selectionListener);
    }

    @Override
    @Transactional(readOnly = true)
    protected void save() {
        User editedUser = model.getEditedEntity();
        service.saveUser(editedUser, model.isNewMode());
        mailConfirmationService.sendEmail(editedUser);
    }

	public void prepareResetPassword() {
		model.setPassword(null);
		model.setConfirmPassword(null);
	}

	public void confirmResetPassword() {
		User editedUser = model.getEditedEntity();
		editedUser.setPassword(model.getPassword());
		service.saveUser(editedUser, true);
	}

    @Override
    protected AbstractModel<User> getModel() {
        return model;
    }

    @Override
    protected Service<User> getService() {
        return service;
    }

    protected Class<User> getEntityClass() {
        return User.class;
    }
}
