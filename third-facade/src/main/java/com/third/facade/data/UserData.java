package com.third.facade.data;

import java.util.List;

/**
 * user data
 */
public class UserData extends AbstractData {

	public String userId;
	public String name;
	public String password;
	public boolean blocked;
	public UserGroupData userGroup;
	public List<StoreData> stores;
	private StoreData store;

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setUserGroup(UserGroupData userGroup)
	{
		this.userGroup = userGroup;
	}

	public UserGroupData getUserGroup()
	{
		return userGroup;
	}

	public boolean isBlocked()
	{
		return blocked;
	}

	public void setBlocked(boolean blocked)
	{
		this.blocked = blocked;
	}

	public List<StoreData> getStores()
	{
		return stores;
	}

	public void setStores(List<StoreData> stores)
	{
		this.stores = stores;
	}

	public boolean equals(UserData user)
	{
		return this.userId.equals(user.getUserId());
	}

	public StoreData getStore()
	{
		return store;
	}

	public void setStore(StoreData store)
	{
		this.store = store;
	}
}
