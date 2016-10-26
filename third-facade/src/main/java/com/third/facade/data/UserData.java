package com.third.facade.data;

public class UserData {

	public String id;
	public String name;
	public UserGroupData userGroupData;

	public void setId(String id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setUserGroupData(UserGroupData userGroupData)
	{
		this.userGroupData = userGroupData;
	}

}
