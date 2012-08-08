package org.opensource.climone.controller.administration;

import org.opensource.climone.controller.AbstractController;
import org.opensource.climone.controller.AbstractModel;
import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.service.RoleService;
import org.opensource.climone.service.Service;
import org.opensource.climone.view.datamodel.DefaultSelectionDataModelListener;
import org.opensource.climone.view.datamodel.ListDataModel;
import org.opensource.climone.view.datamodel.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Controller("roleController")
public class RoleController extends AbstractController<Role> {

	@Autowired
	private RoleService service;

	@Autowired
	private RoleModel model;

	public void init() {
		model.setFilter(new RoleFilter());
	}

    @Override
    protected void bindEntityToModel() {
        Role editedRole = model.getEditedEntity();
        model.setName(editedRole.getName());
        model.setDescription(editedRole.getDescription());

        DefaultSelectionDataModelListener<Permission> selectionListener = new DefaultSelectionDataModelListener<Permission>(
                new HashSet<Permission>(editedRole.getPermissions()));
        SelectableDataModel<Permission> permissionDataModel = new SelectableDataModel<Permission>(
                service.getAllPermissions(), selectionListener);
        model.setPermissionsDataModel(permissionDataModel);
        model.setPermissionSelectionListener(selectionListener);
    }

    @Override
    protected void bindModelToEntity() {
        Role editedRole = model.getEditedEntity();
        editedRole.setName(model.getName());
        editedRole.setDescription(model.getDescription());
        editedRole.getPermissions().clear();
        editedRole.getPermissions().addAll(model.getPermissionSelectionListener().getSelectedElements());
    }

    @Override
    protected AbstractModel<Role> getModel() {
        return model;
    }

    @Override
    protected Service<Role> getService() {
        return service;
    }

    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
