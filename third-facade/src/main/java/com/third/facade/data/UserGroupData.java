package com.third.facade.data;

import java.util.List;

public class UserGroupData extends AbstractData {

	public String groupId;
	public String name;
	public List<RoleData> roles;

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<RoleData> getRoles()
	{
		return roles;
	}

	public void setRoles(List<RoleData> roles)
	{
		this.roles = roles;
	}

}
