package com.third.facade.testdata.builder;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.third.model.MenuModel;
import com.third.model.RoleModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.store.StoreService;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.UserService;

/**
 * build test data for user module,this can be run via /data url,implement
 * {@link DataBuilder}
 */
public class UserDataBuilder implements DataBuilder {
	private UserService userService;
	private MenuService menuService;
	private RoleService roleService;
	private StoreService storeService;

	public void buildData()
	{

		/*
		 * 创建符合easy-ui的菜单,如果需要启用easyui需要将这段注释掉 MenuModel lv2_user =
		 * this.buildMenu("1", 2, "系统管理", "#", "menu-icon-sys");
		 * menuService.createMenu(lv2_user);
		 * 
		 * MenuModel lv3_usergroup = this.buildMenu("11", 3, "用户组列表",
		 * "/getUserGroupListPage", "menu-icon-role"); MenuModel lv3_userlist =
		 * this.buildMenu("12", 3, "用户列表", "/getUserListPage",
		 * "menu-icon-users"); lv3_usergroup.setParentMenu(lv2_user);
		 * lv3_userlist.setParentMenu(lv2_user);
		 * menuService.createMenu(lv3_usergroup);
		 * menuService.createMenu(lv3_userlist);
		 * 
		 * MenuModel lv2_sales = this.buildMenu("2", 2, "销售管理", "#",
		 * "menu-icon-users"); menuService.createMenu(lv2_sales);
		 * 
		 * MenuModel lv3_customer = this.buildMenu("21", 3, "顾客管理",
		 * "/getCustomerListPage", "menu-icon-role");
		 * lv3_customer.setParentMenu(lv2_sales);
		 * menuService.createMenu(lv3_userlist);
		 * 
		 * MenuModel lv3_orders = this.buildMenu("22", 3, "订单管理",
		 * "/getOrderListPage", "menu-icon-file");
		 * lv3_orders.setParentMenu(lv2_sales);
		 * menuService.createMenu(lv3_orders);
		 * 
		 * MenuModel lv3_reservation = this.buildMenu("23", 3, "预约管理",
		 * "/getReservationListPage", "menu-icon-file");
		 * lv3_reservation.setParentMenu(lv2_sales);
		 * menuService.createMenu(lv3_reservation);
		 * 
		 * MenuModel lv3_orderprocess = this.buildMenu("24", 3, "条码管理",
		 * "/getOrderProcessPage", "menu-icon-file");
		 * lv3_orderprocess.setParentMenu(lv2_sales);
		 * menuService.createMenu(lv3_orderprocess);
		 * 
		 * MenuModel lv3_orderprocesslist = this.buildMenu("25", 3, "扫码记录查询管理",
		 * "/getOrderProcessListPage", "menu-icon-file");
		 * lv3_orderprocesslist.setParentMenu(lv2_sales);
		 * menuService.createMenu(lv3_orderprocesslist);
		 */

		MenuModel lv2_user = this.buildMenu("1", 2, "系统管理", "#", "fa-opera");
		menuService.createMenu(lv2_user);

		// MenuModel lv3_usergroup = this.buildMenu("11", 3, "用户组列表",
		// "/user/usergrouplistpage", "fa-users");
		MenuModel lv3_userlist = this.buildMenu("11", 3, "用户列表",
				"/user/userlistpage", "fa-user");
		// lv3_usergroup.setParentMenu(lv2_user);
		lv3_userlist.setParentMenu(lv2_user);
		// menuService.createMenu(lv3_usergroup);
		menuService.createMenu(lv3_userlist);

		MenuModel lv3_source = this.buildMenu("12", 3, "顾客来源管理",
				"/source/listpage", "fa-users");
		lv3_source.setParentMenu(lv2_user);
		menuService.createMenu(lv3_source);

		MenuModel lv2_sales = this.buildMenu("2", 2, "销售管理", "#", "fa-book");
		menuService.createMenu(lv2_sales);

		MenuModel lv3_customer = this.buildMenu("21", 3, "顾客管理",
				"/customer/customerlistpage", "fa-users");
		lv3_customer.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_userlist);

		MenuModel lv3_orders = this.buildMenu("22", 3, "订单管理",
				"/order/orderlistpage", "fa-book");
		lv3_orders.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_orders);

		MenuModel lv3_reservation = this.buildMenu("23", 3, "创建订单",
				"/order/createorderpage", "fa-edit");
		lv3_reservation.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_reservation);

		MenuModel lv3_orderprocess = this.buildMenu("24", 3, "订单处理记录查询",
				"/orderprocess/orderprocesspage", "fa-barcode");
		lv3_orderprocess.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_orderprocess);

		MenuModel lv3_exhibition = this.buildMenu("25", 3, "展会管理",
				"/salesquation/salesquationlistpage", "fa-search");
		lv3_exhibition.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_exhibition);

		MenuModel lv3_reservationlist = this.buildMenu("26", 3, "预约管理",
				"/reservation/reservationlistpage", "fa-search");
		lv3_reservationlist.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_reservationlist);

		MenuModel lv3_orderentrylist = this.buildMenu("27", 3, "量身单列表",
				"/orderentry/listpage", "fa-book");
		lv3_orderentrylist.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_orderentrylist);

		MenuModel lv3_storereceipt = this.buildMenu("28", 3, "门店收货",
				"/store/orderreceipt", "fa-barcode");
		lv3_storereceipt.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_storereceipt);
		MenuModel lv3_storedeliver = this.buildMenu("29", 3, "顾客取件",
				"/store/orderdeliver", "fa-barcode");
		lv3_storedeliver.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_storedeliver);

		MenuModel lv3_source1 = this.buildMenu("30", 3, "顾客来源管理",
				"/source/listforstorepage", "fa-users");
		lv3_source1.setParentMenu(lv2_sales);
		menuService.createMenu(lv3_source1);

		MenuModel lv2_factory = this.buildMenu("3", 2, "工厂管理", "#",
				"fa-building");
		menuService.createMenu(lv2_sales);

		MenuModel lv3_factorydeliver = this.buildMenu("32", 3, "扫码发货",
				"/factory/orderdeliver", "fa-truck");
		lv3_customer.setParentMenu(lv2_sales);
		lv3_factorydeliver.setParentMenu(lv2_factory);

		MenuModel lv3_orderentrylist1 = this.buildMenu("33", 3, "量身单列表",
				"/orderentry/listpage", "fa-book");
		lv3_orderentrylist1.setParentMenu(lv2_factory);
		menuService.createMenu(lv3_orderentrylist1);

		MenuModel lv3_product = this.buildMenu("34", 3, "布料列表",
				"/product/productlistpage", "fa-database");
		lv3_product.setParentMenu(lv2_factory);
		menuService.createMenu(lv3_product);

		MenuModel lv2_report = this.buildMenu("4", 2, "财务报表", "#", "fa-money");
		menuService.createMenu(lv2_report);

		MenuModel lv3_report1 = this.buildMenu("41", 3, "付款明细",
				"/payment/listpage", "fa-money");
		lv3_report1.setParentMenu(lv2_report);
		menuService.createMenu(lv3_report1);

		List<MenuModel> menus = Arrays.asList(lv3_userlist, lv3_source,
				lv3_customer, lv3_orders, lv3_reservation, lv3_orderprocess,
				lv3_source1, lv3_reservationlist,
				lv3_storereceipt, lv3_storedeliver, lv3_orderentrylist,lv3_exhibition,
				lv3_factorydeliver, lv3_orderentrylist1, lv3_product,
				lv3_report1);
		// create role

		RoleModel role_admin = buildRole("admin", "管理员", "管理员", menus);
		RoleModel role_sales = buildRole("sales", "销售员", "销售员", menus);
		RoleModel role_factory = buildRole("factory", "工厂", "工厂", menus);
		RoleModel role_finicial = buildRole("finicial", "财务", "财务", menus);

		UserModel admin = new UserModel();
		admin.setUserId("test");
		admin.setName("test user");
		admin.setPassword("test");
		UserGroupModel userGroup = this.buildUserGroupModel("admin", "管理员",
				role_admin);
		List<UserGroupModel> userGroups = Arrays.asList(
				this.buildUserGroupModel("sales", "销售员", role_sales),
				this.buildUserGroupModel("factory", "工厂", role_factory),
				this.buildUserGroupModel("finicial", "财务", role_finicial));

		admin.setUserGroup(userGroup);
		userService.createUserGroup(userGroup);
		userService.createUser(admin);

		for (int i = 0; i < 50; i++)
		{
			UserModel u = new UserModel();
			u.setUserId("test" + i);
			final int j = RandomUtils.nextInt(0, 3);
			u.setName("test user" + i + "-" + j);
			u.setPassword("test");
			u.setUserGroup(userGroups.get(j));
			u.setStore(storeService.getStoreForCode("s-1"));
			u.setStores(storeService.getAllStores());
			userService.createUser(u);
		}
	}

	protected MenuModel buildMenu(final String menuId, final Integer level,
			final String menuName, final String url, final String icon)
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

	public UserGroupModel buildUserGroupModel(final String id,
			final String name, final RoleModel role)
	{
		UserGroupModel userGroup = new UserGroupModel();
		userGroup.setGroupId(id);
		userGroup.setName(name);
		userGroup.setRoles(Arrays.asList(role));
		userService.createUserGroup(userGroup);
		return userGroup;
	}

	public RoleModel buildRole(final String id, final String name,
			final String desc, final List<MenuModel> menus)
	{
		RoleModel role = new RoleModel();
		role.setRoleId(id);
		role.setRoleName(name);
		role.setDescription(desc);
		role.setMenus(menus);

		return role;
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

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

}
