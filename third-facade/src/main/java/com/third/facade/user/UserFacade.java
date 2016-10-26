package com.third.facade.user;

import java.util.List;

import com.third.facade.data.MenuData;
import com.third.facade.data.UserData;


public interface UserFacade
{

	public void createUser(UserData user);

	public List<MenuData> getMenuData();
}
