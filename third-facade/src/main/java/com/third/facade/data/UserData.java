package com.third.facade.data;

/**
 * user data
 */
public class UserData extends AbstractData
{

	public String userId;
	public String name;
	public UserGroupData userGroupData;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public UserGroupData getUserGroupData()
	{
		return userGroupData;
	}

	public void setUserGroupData(UserGroupData userGroupData)
	{
		this.userGroupData = userGroupData;
	}

}
