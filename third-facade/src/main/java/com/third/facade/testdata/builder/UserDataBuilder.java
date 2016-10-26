package com.third.facade.testdata.builder;

import java.util.Arrays;

import com.third.model.MenuModel;
import com.third.model.RoleModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.UserService;


public class UserDataBuilder implements DataBuilder
{
	private UserService userService;
	private MenuService menuService;
	private RoleService roleService;

	public void buildData()
	{
		UserModel admin = new UserModel();
		admin.setUserId("yuxiang");
		admin.setName("与非");
		admin.setPassword("密码");
		UserGroupModel userGroup = new UserGroupModel();
		userGroup.setGroupId("admin");
		userGroup.setName("管理员");
		userService.createUserGroup(userGroup);

		admin.setUserGroup(userGroup);
		userService.createUser(admin);


		MenuModel lv2_user = this.buildMenu("1", 2, "用户管理", "#", "icon-sys");
		menuService.createMenu(lv2_user);

		MenuModel lv3_usergroup = this.buildMenu("11", 3, "用户组列表", "/usergrouplistpage", "icon-role");
		MenuModel lv3_userlist = this.buildMenu("12", 3, "用户列表", "#", "icon-users");
		lv3_usergroup.setParentMenu(lv2_user);
		lv3_userlist.setParentMenu(lv2_user);
		menuService.createMenu(lv3_usergroup);
		menuService.createMenu(lv3_userlist);

		MenuModel lv2_test = this.buildMenu("2", 2, "二级测试结点", "#", "icon-users");
		menuService.createMenu(lv2_test);

		//create role
		RoleModel role = new RoleModel();
		role.setRoleId("adminRole");
		role.setRoleName("管理员角色");
		role.setMenus(Arrays.asList(lv3_usergroup, lv3_userlist, lv2_test));

		roleService.createRole(role);

		userGroup.setRoles(Arrays.asList(role));
		userService.saveUserGroup(userGroup);
	}

	protected MenuModel buildMenu(final String menuId, final Integer level, final String menuName, final String url,
			final String icon)
	{
		MenuModel menu = new MenuModel();
		menu.setMenuId(menuId);
		menu.setLevel(level);
		menu.setMenuName(menuName);
		menu.setUrl(url);
		menu.setIcon(icon);
		menuService.createMenu(menu);
		return menu;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public void setMenuService(MenuService menuService)
	{
		this.menuService = menuService;
	}

	public void setRoleService(RoleService roleService)
	{
		this.roleService = roleService;
	}


}
