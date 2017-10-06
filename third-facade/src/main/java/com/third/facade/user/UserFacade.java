package com.third.facade.user;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.facade.data.ListData;
import com.third.facade.data.MenuData;
import com.third.facade.data.RoleData;
import com.third.facade.data.UserData;
import com.third.facade.data.UserGroupData;

public interface UserFacade {

	/**
	 * @param user
	 */
	public void createUser(UserData user);

	/**
	 * @return
	 */
	public List<MenuData> getMenuData();
	
	public List<MenuData> getMenuDataForMobile();

	/**
	 * @param userGroupName
	 * @param userGroupId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	ListData getUserGroups(String userGroupName, String userGroupId,
			Integer startIndex, Integer pageSize);

	/**
	 * @param userName
	 * @param userId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	ListData getUsers(String userName, String userId, Integer startIndex,
			Integer pageSize);

	/**
	 * @param userGroup
	 */
	void createUserGroup(UserGroupData userGroup);

	/**
	 * @param userGroup
	 */
	void updateUserGroup(UserGroupData userGroup);

	/**
	 * @return
	 */
	List<RoleData> getRoles();

	/**
	 * @param userGroupPK
	 * @return
	 */
	List<RoleData> getRolesForUserGroup(final String userGroupPK);

	void updateUser(UserData user);

	UserData getUserById(final String userId);

	UserData getCurrentUser();

	List<UserData> getSalesPerson(final String storeCode);

	boolean isAdmin();
	
	boolean isSalesperson();
	
	boolean isFactory();
	
	void loginSuccess(final String userId);

	void logout();

	boolean isFinicial();
	
	List<UserData> getDesignerForStore(final String storeCode);

	boolean isManager();
}
