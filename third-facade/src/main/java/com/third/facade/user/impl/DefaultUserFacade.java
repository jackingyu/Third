package com.third.facade.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.third.facade.data.MenuData;
import com.third.facade.data.UserData;
import com.third.facade.populator.MenuDataPopulator;
import com.third.facade.user.UserFacade;
import com.third.model.MenuModel;
import com.third.model.RoleModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.MenuService;
import com.third.service.user.UserService;


public class DefaultUserFacade implements UserFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultUserFacade.class);

	private MenuService menuService;
	private MenuDataPopulator menuDataPopulator;
	private UserService userService;

	public void setMenuService(MenuService menuService)
	{
		this.menuService = menuService;
	}

	public void setMenuDataPopulator(MenuDataPopulator menuDataPopulator)
	{
		this.menuDataPopulator = menuDataPopulator;
	}

	public void createUser(UserData user)
	{
		UserModel userModel = new UserModel();

	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuData> getMenuData()
	{
		UserGroupModel userGroup = userService.getCurrentUser().getUserGroup();
		Collection<RoleModel> userRoles = userGroup.getRoles();
		//already handled level 2 menu,this is the result we want
		Map<String, MenuData> lv2Menus = new HashMap<String, MenuData>();
		//already handled level 3 menu
		Map<String, MenuData> lv3Menus = new HashMap<String, MenuData>();
		List<MenuData> menuDataList = new ArrayList<MenuData>();

		//loop the roles,delete the duplicated
		userRoles.forEach(role -> role.getMenus().forEach(m -> {
			MenuModel menu = (MenuModel) m;

			if (menu.getLevel() == 2)
			{
				if (!lv2Menus.containsKey(menu.getMenuId()))
				{
					MenuData lv2MenuData = new MenuData();
					menuDataPopulator.populate(menu, lv2MenuData);
					lv2Menus.put(menu.getMenuId(), lv2MenuData);
				}
			}
			else
			{
				if (!lv3Menus.containsKey(menu.getMenuId()))
				{
					MenuData lv3MenuData = new MenuData();
					menuDataPopulator.populate(menu, lv3MenuData);
					lv3Menus.put(lv3MenuData.getMenuid(), lv3MenuData);

					if (menu.getParentMenu() != null)
					{
						LOG.info("get parent menu " + menu.getMenuName());
						MenuModel parentMenu = menu.getParentMenu();

						if (lv2Menus.containsKey(parentMenu.getMenuId()))
						{
							lv2Menus.get(parentMenu.getMenuId()).addSubMenu(lv3MenuData);
						}
						else
						{
							MenuData parentMenuData = new MenuData();
							menuDataPopulator.populate(parentMenu, parentMenuData);
							parentMenuData.addSubMenu(lv3MenuData);
							lv2Menus.put(parentMenuData.getMenuid(), parentMenuData);
						}
					}

				}
			}
		}));

		return new ArrayList<MenuData>(lv2Menus.values());
	}

}
