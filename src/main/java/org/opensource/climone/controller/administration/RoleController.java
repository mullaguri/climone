package org.opensource.climone.controller.administration;

import java.util.HashSet;
import java.util.List;

import javax.faces.model.DataModel;

import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.entities.security.RoleFilter;
import org.opensource.climone.service.RoleService;
import org.opensource.climone.view.datamodel.DefaultSelectionDataModelListener;
import org.opensource.climone.view.datamodel.ListDataModel;
import org.opensource.climone.view.datamodel.SelectableDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller("roleController")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleModel roleModel;

	public void init() {
		roleModel.setFilter(new RoleFilter());
	}

	public void search() {
		List<Role> roles = roleService.getList(roleModel.getFilter());

		DataModel<Role> dataModel = new ListDataModel<Role>(roles);

		roleModel.setRoleList(dataModel);
		roleModel.setPageNumber(1);
	}

	public void prepareNew() {
		Role editedRole = new Role();
		editedRole.setPermissions(new HashSet<Permission>());
		roleModel.setEditedRole(editedRole);
		roleModel.setName(null);
		roleModel.setDescription(null);

		final DefaultSelectionDataModelListener<Permission> selectionListener = new DefaultSelectionDataModelListener<Permission>(
				new HashSet<Permission>());
		final SelectableDataModel<Permission> permissionDataModel = new SelectableDataModel<Permission>(
				roleService.getAllPermissions(), selectionListener);
		roleModel.setPermissionsDataModel(permissionDataModel);
		roleModel.setPermissionSelectionListener(selectionListener);

	}

	public void prepareEdit() {
		Role editedRole = roleModel.getEditedRole();
		roleModel.setName(editedRole.getName());
		roleModel.setDescription(editedRole.getDescription());

		DefaultSelectionDataModelListener<Permission> selectionListener = new DefaultSelectionDataModelListener<Permission>(
				new HashSet<Permission>(editedRole.getPermissions()));
		SelectableDataModel<Permission> permissionDataModel = new SelectableDataModel<Permission>(
				roleService.getAllPermissions(), selectionListener);
		roleModel.setPermissionsDataModel(permissionDataModel);
		roleModel.setPermissionSelectionListener(selectionListener);

	}

	public void delete() {
		roleService.deleteRole(roleModel.getEditedRole());
		this.search();
	}

	@Transactional
	public void confirmSave() {
		Role editedRole = roleModel.getEditedRole();
		editedRole.setName(roleModel.getName());
		editedRole.setDescription(roleModel.getDescription());
		editedRole.getPermissions().clear();
		editedRole.getPermissions().addAll(
				roleModel.getPermissionSelectionListener()
						.getSelectedElements());
		roleService.saveRole(editedRole);
	}
}
