package com.third.service.user.impl;

import com.third.dao.user.RoleDao;
import com.third.model.RoleModel;
import com.third.service.user.RoleService;


public class DefaultRoleService implements RoleService
{

	private RoleDao roleDao;

	@Override
	public void createRole(RoleModel role)
	{
		roleDao.save(role);
	}

	public void setRoleDao(RoleDao roleDao)
	{
		this.roleDao = roleDao;
	}

}
