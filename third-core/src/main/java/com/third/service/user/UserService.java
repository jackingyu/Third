package com.third.service.user;

import com.third.exceptions.SystemException;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;


/**
 * @author jack
 *
 */
public interface UserService
{
	/**
	 * @param userId
	 * @return
	 */
	public UserModel getUserById(String userId);

	/**
	 * @param useGroupdId
	 * @return
	 */
	public UserGroupModel getUserGroupById(String useGroupdId);

	/**
	 * @param user
	 */
	public void saveUser(UserModel user);

	/**
	 * 检查用户是否存在
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isExist(String userId);

	/**
	 * @return
	 */
	public UserModel getCurrentUser();

	/**
	 * @param user
	 * @return
	 */
	public void createUser(UserModel user);

	/**
	 * @param userGroup
	 * @return
	 */
	public void createUserGroup(UserGroupModel userGroup);

	/**
	 * @param userGroup
	 */
	public void saveUserGroup(UserGroupModel userGroup);

}
