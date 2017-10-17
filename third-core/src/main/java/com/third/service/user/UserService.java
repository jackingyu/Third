package com.third.service.user;

import java.util.List;

import com.third.dao.util.PaginationSupport;
import com.third.exceptions.SystemException;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;

/**
 * @author jack
 *
 */
public interface UserService {
	/**
	 * @param userId
	 * @return
	 */
	public UserModel getUserById(final String userId);

	/**
	 * @param useGroupdId
	 * @return
	 */
	public UserGroupModel getUserGroupById(final String userGroupId);

	/**
	 * @param pk
	 * @return
	 */
	UserGroupModel getUserGroup(final String pk);

	/**
	 * 检查用户是否存在
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isExist(final String userId);

	/**
	 * @return
	 */
	public UserModel getCurrentUser();

	/**
	 * @param user
	 * @return
	 */
	public void createUser(final UserModel user);

	/**
	 * @param userGroup
	 * @return
	 */
	public void createUserGroup(final UserGroupModel userGroup);

	/**
	 * @param userGroup
	 */
	public void updateUserGroup(final UserGroupModel userGroup);

	/**
	 * @param userGroupId
	 * @param userGroupName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	PaginationSupport getUserGroupList(final String userGroupId,
			final String userGroupName, final Integer startIndex,
			final Integer pageSize);

	/**
	 * @param user
	 */
	void updateUser(final UserModel user);

	/**
	 * @param userId
	 * @param userName
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	PaginationSupport getUserList(String userId, String userName,String storeCode,
			Integer startIndex, Integer pageSize);

	List<UserModel> getSalesPerson(final String storeCode);
	
	List<UserModel> getDesignerForStore(final String storeCode);
	
	boolean checkUserGroup(final String userGroup);
	
	String generateUserId();

}
