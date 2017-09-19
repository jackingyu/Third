package com.third.facade.populator;

import com.third.facade.data.MenuData;
import com.third.model.MenuModel;

public class MenuDataPopulator implements Populator<MenuModel, MenuData> {

	@Override
	public void populate(MenuModel source, MenuData target)
	{
		target.setMenuid(source.getMenuId());
		target.setMenuname(source.getMenuName());
		target.setUrl(source.getUrl());
		target.setIcon(source.getIcon());
		target.setMobile(source.getMobile());
	}

}
