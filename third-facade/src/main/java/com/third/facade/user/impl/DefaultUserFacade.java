package com.third.facade.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ListData;
import com.third.facade.data.MenuData;
import com.third.facade.data.RoleData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;
import com.third.facade.populator.MenuDataPopulator;
import com.third.facade.populator.RoleDataPopulator;
import com.third.facade.populator.UserDataPopulator;
import com.third.facade.populator.UserGroupDataPopulator;
import com.third.facade.user.UserFacade;
import com.third.model.MenuModel;
import com.third.model.RoleModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.UserService;


public class DefaultUserFacade implements UserFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultUserFacade.class);

	private MenuService menuService;
	private UserService userService;
	private RoleService roleService;

	private MenuDataPopulator menuDataPopulator;
	private UserGroupDataPopulator userGroupDataPopulator;
	private RoleDataPopulator roleDataPopulator;
	private UserDataPopulator userDataPopulator;

	public void createUser(UserData user)
	{
		UserModel userModel = new UserModel();

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

	@Override
	public ListData getUserGroups(final String userGroupName, final String userGroupId, final Integer startIndex,
			final Integer pageSize)
	{
		PaginationSupport result = userService.getUserGroupList(userGroupId, userGroupName, startIndex, pageSize);
		ListData grid = new ListData();
		grid.setTotal(result.getTotalCount());
		List<Object> userGroups = new ArrayList<Object>();
		result.getItems().forEach(n -> {
			UserGroupData userGroupData = new UserGroupData();
			userGroupDataPopulator.populate((UserGroupModel) n, userGroupData);
			userGroups.add(userGroupData);
		});

		grid.setRows(userGroups);
		return grid;
	}

	@Override
	public void updateUserGroup(UserGroupData userGroup)
	{
		//TODO:修改为从PK拉取,所有的Data继承自一个基础类,增加PK字段
		UserGroupModel userGroupModel = userService.getUserGroup(userGroup.getPk());
		userGroupModel.setGroupId(userGroup.getGroupId());
		userGroupModel.setName(userGroup.getName());

		List<RoleData> roleDatas = userGroup.getRoles();

		if (!CollectionUtils.isEmpty(roleDatas))
		{
			List<RoleModel> roles = new ArrayList<RoleModel>();
			roleDatas.forEach(rd -> {
				RoleModel role = roleService.getRole(rd.getPk());
				roles.add(role);
			});

			userGroupModel.setRoles(roles);
		}
		userService.updateUserGroup(userGroupModel);
	}

	@Override
	public List<RoleData> getRoles()
	{
		List<RoleData> roles = new ArrayList<RoleData>();
		roleService.getRoles().forEach(r -> {
			RoleData rd = new RoleData();
			roleDataPopulator.populate(r, rd);
			roles.add(rd);
		});

		return roles;
	}

	@Override
	public List<RoleData> getRolesForUserGroup(String userGroupPK)
	{
		UserGroupModel userGroupModel = userService.getUserGroup(userGroupPK);
		List<RoleModel> roles = (List<RoleModel>) userGroupModel.getRoles();
		List<RoleData> roleDatas = new ArrayList<RoleData>();

		roles.forEach(r -> {
			RoleData rd = new RoleData();
			roleDataPopulator.populate(r, rd);
			roleDatas.add(rd);
		});

		return roleDatas;
	}

	@Override
	public ListData getUsers(String userId, String userName, Integer startIndex, Integer pageSize)
	{
		PaginationSupport result = userService.getUserList(userId, userName, startIndex, pageSize);
		ListData grid = new ListData();
		grid.setTotal(result.getTotalCount());
		List<Object> users = new ArrayList<Object>();
		result.getItems().forEach(n -> {
			UserData userData = new UserData();
			userDataPopulator.populate((UserModel) n, userData);
			users.add(userData);
		});

		grid.setRows(users);
		return grid;
	}

	public void setUserDataPopulator(UserDataPopulator userDataPopulator)
	{
		this.userDataPopulator = userDataPopulator;
	}

	public void setMenuService(MenuService menuService)
	{
		this.menuService = menuService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}

	public void setMenuDataPopulator(MenuDataPopulator menuDataPopulator)
	{
		this.menuDataPopulator = menuDataPopulator;
	}

	public void setUserGroupDataPopulator(UserGroupDataPopulator userGroupDataPopulator)
	{
		this.userGroupDataPopulator = userGroupDataPopulator;
	}

	public void setRoleDataPopulator(RoleDataPopulator roleDataPopulator)
	{
		this.roleDataPopulator = roleDataPopulator;
	}

}
