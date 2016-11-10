package com.third.facade.testdata.builder;

import java.util.Arrays;

import com.third.model.MenuModel;
import com.third.model.RoleModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.UserService;


/**
 * build test data for user module,this can be run via /data url,implement {@link DataBuilder}
 */
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

		for (int i = 0; i < 100; i++)
		{
			UserGroupModel userGroup1 = new UserGroupModel();
			userGroup1.setGroupId("admin" + i);
			userGroup1.setName("管理员" + i);
			userService.createUserGroup(userGroup1);
		}

		admin.setUserGroup(userGroup);
		userService.createUser(admin);

		for (int i = 0; i < 100; i++)
		{
			UserModel user = new UserModel();
			user.setUserId("测试用户" + i);
			user.setName("测试用户" + i);
			user.setPassword("密码");
			userService.createUser(user);
		}

		MenuModel lv2_user = this.buildMenu("1", 2, "系统管理", "#", "menu-icon-sys");
		menuService.createMenu(lv2_user);

		MenuModel lv3_usergroup = this.buildMenu("11", 3, "用户组列表", "/getUserGroupListPage", "menu-icon-role");
		MenuModel lv3_userlist = this.buildMenu("12", 3, "用户列表", "/getUserListPage", "menu-icon-users");
		lv3_usergroup.setParentMenu(lv2_user);
		lv3_userlist.setParentMenu(lv2_user);
		menuService.createMenu(lv3_usergroup);
		menuService.createMenu(lv3_userlist);

		MenuModel lv2_sales = this.buildMenu("2", 2, "销售管理", "#", "menu-icon-users");
		menuService.createMenu(lv2_sales);

		MenuModel lv3_customer = this.buildMenu("21", 3, "顾客管理", "/getCustomerListPage", "menu-icon-role");
		lv3_customer.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_userlist);

		MenuModel lv3_orders = this.buildMenu("22", 3, "订单管理", "/getOrderListPage", "menu-icon-file");
		lv3_orders.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_orders);

		//create role
		RoleModel role = new RoleModel();
		role.setRoleId("adminRole");
		role.setRoleName("管理员角色");
		role.setDescription("测试管理员的角色的描述文本的橘色");
		role.setMenus(Arrays.asList(lv3_usergroup, lv3_userlist, lv3_customer, lv3_orders));

		roleService.createRole(role);


		for (int i = 0; i < 20; i++)
		{
			RoleModel role1 = new RoleModel();
			role1.setRoleId("role" + i);
			role1.setRoleName("角色" + i);
			role1.setDescription("描述描述描述描述" + i);
			roleService.createRole(role1);
		}

		userGroup.setRoles(Arrays.asList(role));
		userService.createUserGroup(userGroup);
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
