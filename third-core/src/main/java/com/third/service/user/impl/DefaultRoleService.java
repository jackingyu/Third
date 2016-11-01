package com.third.service.user.impl;

import java.util.List;

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

	public List<RoleModel> getRoles()
	{
		return roleDao.list();
	}

	@Override
	public RoleModel getRole(String PK)
	{
		return roleDao.get(PK);
	}

}
