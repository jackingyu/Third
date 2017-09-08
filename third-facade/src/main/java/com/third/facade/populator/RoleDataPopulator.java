package com.third.facade.populator;

import com.third.facade.data.MenuData;
import com.third.facade.data.RoleData;
import com.third.model.MenuModel;
import com.third.model.RoleModel;

public class RoleDataPopulator implements Populator<RoleModel, RoleData> {

	@Override
	public void populate(RoleModel source, RoleData target)
	{
		target.setPk(source.getPk());
		target.setRoleId(source.getRoleId());
		target.setRoleName(source.getRoleName());
		target.setDescription(source.getDescription());

	}

}
