package org.opensource.climone.controller.administration;

import org.opensource.climone.controller.AbstractModel;
import org.opensource.climone.entities.security.Permission;
import org.opensource.climone.entities.security.Role;
import org.opensource.climone.view.datamodel.DefaultSelectionDataModelListener;
import org.opensource.climone.view.datamodel.SelectableDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

@Controller("roleModel")
@Scope(value = "flow", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleModel extends AbstractModel<Role> {

	private static final long serialVersionUID = -835255549908549635L;

	private String name;
	private String description;
	private SelectableDataModel<Permission> permissionsDataModel;
	private DefaultSelectionDataModelListener<Permission> permissionSelectionListener;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SelectableDataModel<Permission> getPermissionsDataModel() {
		return permissionsDataModel;
	}

	public void setPermissionsDataModel(SelectableDataModel<Permission> permissionsDataModel) {
		this.permissionsDataModel = permissionsDataModel;
	}

	public DefaultSelectionDataModelListener<Permission> getPermissionSelectionListener() {
		return permissionSelectionListener;
	}

	public void setPermissionSelectionListener(DefaultSelectionDataModelListener<Permission> permissionSelectionListener) {
		this.permissionSelectionListener = permissionSelectionListener;
	}

}
