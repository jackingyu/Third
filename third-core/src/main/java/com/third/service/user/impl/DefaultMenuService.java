package com.third.service.user.impl;

import com.third.dao.user.MenuDao;
import com.third.model.MenuModel;
import com.third.service.user.MenuService;


public class DefaultMenuService implements MenuService
{
	private MenuDao menuDao;

	@Override
	public void createMenu(MenuModel menu)
	{
		menuDao.save(menu);
	}

	public void setMenuDao(MenuDao menuDao)
	{
		this.menuDao = menuDao;
	}

}
