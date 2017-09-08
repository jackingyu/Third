package com.third.service.user;

import java.util.List;

import com.third.model.RoleModel;

public interface RoleService {
	/**
	 * @param role
	 */
	public void createRole(RoleModel role);

	/**
	 * @return
	 */
	public List<RoleModel> getRoles();

	/**
	 * @param PK
	 * @return
	 */
	public RoleModel getRole(final String PK);
}
