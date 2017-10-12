package com.third.facade.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.core.constants.CoreConstants;
import com.third.core.constants.UserGroupConstants;
import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ComboboxData;
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
import com.third.model.StoreModel;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import com.third.service.store.StoreService;
import com.third.service.user.MenuService;
import com.third.service.user.RoleService;
import com.third.service.user.SessionService;
import com.third.service.user.UserService;

public class DefaultUserFacade implements UserFacade {
	private static final Logger LOG = Logger.getLogger(DefaultUserFacade.class);

	private MenuService menuService;
	private UserService userService;
	private RoleService roleService;
	private SessionService sessionService;
	private StoreService storeService;

	private MenuDataPopulator menuDataPopulator;
	private UserGroupDataPopulator userGroupDataPopulator;
	private RoleDataPopulator roleDataPopulator;
	private UserDataPopulator userDataPopulator;

	public void createUser(UserData user)
	{
		UserModel userModel = new UserModel();
		userModel.setName(user.getName());
		userModel.setUserId(user.getUserId());
		userModel.setPassword(
				user.getPassword() != null ? user.getPassword() : "111111");
		if (null != user.getUserGroup())
			userModel.setUserGroup(
					userService.getUserGroup(user.getUserGroup().getPk()));
		userModel.setBlocked(user.isBlocked());

		List<StoreModel> stores = new ArrayList<StoreModel>();
		user.stores.forEach(s -> {
			StoreModel store = storeService.getStoreForCode(s.getCode());
			stores.add(store);
		});
		userModel.setStores(stores);
		userModel.setStore(storeService.getStoreForCode(user.getStore().getCode()));

		userService.createUser(userModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuData> getMenuData()
	{
		UserGroupModel userGroup = userService.getCurrentUser().getUserGroup();
		Collection<RoleModel> userRoles = userGroup.getRoles();
		// already handled level 2 menu,this is the result we want
		Map<String, MenuData> lv2Menus = new HashMap<String, MenuData>();
		// already handled level 3 menu
		Map<String, MenuData> lv3Menus = new HashMap<String, MenuData>();
		List<MenuData> menuDataList = new ArrayList<MenuData>();

		// loop the roles,delete the duplicated
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
			} else
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
							lv2Menus.get(parentMenu.getMenuId())
									.addSubMenu(lv3MenuData);
						} else
						{
							MenuData parentMenuData = new MenuData();
							menuDataPopulator.populate(parentMenu,
									parentMenuData);
							parentMenuData.addSubMenu(lv3MenuData);
							lv2Menus.put(parentMenuData.getMenuid(),
									parentMenuData);
						}
					}

				}
			}
		}));

		return new ArrayList<MenuData>(lv2Menus.values());
	}

	@Override
	public ListData getUserGroups(final String userGroupId,
			final String userGroupName, final Integer startIndex,
			final Integer pageSize)
	{
		PaginationSupport result = userService.getUserGroupList(userGroupId,
				userGroupName, startIndex, pageSize);
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
		// TODO:修改为从PK拉取,所有的Data继承自一个基础类,增加PK字段
		UserGroupModel userGroupModel = userService
				.getUserGroup(userGroup.getPk());
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
	public ListData getUsers(String userId, String userName, Integer startIndex,
			Integer pageSize)
	{
		PaginationSupport result = userService.getUserList(userId, userName,
				startIndex, pageSize);
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

	@Override
	public void updateUser(UserData userData)
	{
		UserModel user = userService.getUserById(userData.getUserId());
		user.setName(userData.getName());
		if (null != userData.getUserGroup())
			user.setUserGroup(
					userService.getUserGroup(userData.getUserGroup().getPk()));

		user.setBlocked(userData.isBlocked());

		if (!StringUtils.isEmpty(userData.getPassword()))
			user.setPassword(userData.getPassword());

		List<StoreModel> stores = new ArrayList<StoreModel>();
		userData.stores.forEach(s -> {
			StoreModel store = storeService.getStoreForCode(s.getCode());
			stores.add(store);
		});
		
		user.setStores(stores);
		user.setStore(storeService.getStoreForCode(userData.getStore().getCode()));
		
		userService.updateUser(user);
	}

	@Override
	public UserData getUserById(String userId)
	{
		UserModel userModel = userService.getUserById(userId);
		UserData userData = new UserData();
		UserGroupModel userGroupModel = userModel.getUserGroup();
		UserGroupData userGroupData = new UserGroupData();
		userDataPopulator.populate(userModel, userData);

		if (userGroupModel != null)
			userGroupDataPopulator.populate(userGroupModel, userGroupData);

		userData.setUserGroup(userGroupData);

		return userData;
	}

	@Override
	public void createUserGroup(UserGroupData userGroup)
	{
		UserGroupModel userGroupModel = new UserGroupModel();
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

		userService.createUserGroup(userGroupModel);

	}

	@Override
	public UserData getCurrentUser()
	{
		UserData userData = (UserData) sessionService
				.get(CoreConstants.Session.CURRENT_USER);
		return userData;
	}

	@Override
	public void loginSuccess(String userId)
	{
		UserModel user = userService.getUserById(userId);
		UserData userData = new UserData();
		userDataPopulator.populate(user, userData);
		sessionService.save(CoreConstants.Session.CURRENT_USER, userData);
		sessionService.save(CoreConstants.Session.CURRENT_USER_ID,
				userData.getUserId());
		if(sessionService.isMobile())
		  sessionService.save(CoreConstants.Session.MENU, getMenuDataForMobile());
		else
		  sessionService.save(CoreConstants.Session.MENU, getMenuData());
	}

	@Override
	public void logout()
	{
		sessionService.clear(CoreConstants.Session.CURRENT_USER);
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

	public void setUserGroupDataPopulator(
			UserGroupDataPopulator userGroupDataPopulator)
	{
		this.userGroupDataPopulator = userGroupDataPopulator;
	}

	public void setRoleDataPopulator(RoleDataPopulator roleDataPopulator)
	{
		this.roleDataPopulator = roleDataPopulator;
	}

	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public void setStoreService(StoreService storeService)
	{
		this.storeService = storeService;
	}

	@Override
	public List<UserData> getSalesPerson(String storeCode)
	{
		List<UserModel> userModels = userService.getSalesPerson(storeCode);
		List<UserData> userDatas = new ArrayList<UserData>();

		userModels.forEach(u -> {
			UserData ud = new UserData();
			userDataPopulator.populate(u, ud);
			userDatas.add(ud);
		});

		return userDatas;
	}

	@Override
	public boolean isSalesperson()
	{
		return userService.checkUserGroup(UserGroupConstants.SALES);
	}

	@Override
	public boolean isFactory()
	{
		return userService.checkUserGroup(UserGroupConstants.FACTORY);
	}
	
	@Override
	public boolean isFinicial()
	{
		return userService.checkUserGroup(UserGroupConstants.FINICIAL);
	}
	
	@Override
	public boolean isAdmin()
	{
		return userService.checkUserGroup(UserGroupConstants.ADMIN);
	}
	
	@Override
	public boolean isManager()
	{
		return userService.checkUserGroup(UserGroupConstants.MANAGER);
	}

	@Override
	public List<UserData> getDesignerForStore(String storeCode)
	{
		List<UserModel> userModels = userService.getDesignerForStore(storeCode);
		List<UserData> userDatas = new ArrayList<UserData>();

		userModels.forEach(u -> {
			UserData ud = new UserData();
			userDataPopulator.populate(u, ud);
			userDatas.add(ud);
		});

		return userDatas;
	}

	@Override
	public List<MenuData> getMenuDataForMobile()
	{

		UserGroupModel userGroup = userService.getCurrentUser().getUserGroup();
		Collection<RoleModel> userRoles = userGroup.getRoles();
		// already handled level 2 menu,this is the result we want
		Map<String, MenuData> lv2Menus = new HashMap<String, MenuData>();
		// already handled level 3 menu
		Map<String, MenuData> lv3Menus = new HashMap<String, MenuData>();
		List<MenuData> menuDataList = new ArrayList<MenuData>();

		// loop the roles,delete the duplicated
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
			} else
			{
				if (!lv3Menus.containsKey(menu.getMenuId())&&menu.getMobile())
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
							lv2Menus.get(parentMenu.getMenuId())
									.addSubMenu(lv3MenuData);
						} else
						{
							MenuData parentMenuData = new MenuData();
							menuDataPopulator.populate(parentMenu,
									parentMenuData);
							parentMenuData.addSubMenu(lv3MenuData);
							lv2Menus.put(parentMenuData.getMenuid(),
									parentMenuData);
						}
					}

				}
			}
		}));

		return new ArrayList<MenuData>(lv2Menus.values());
	}

}
