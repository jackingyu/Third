package com.third.dao.user;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserModel;


public interface UserDao extends IGenericDAO<UserModel, String>
{

	/**
	 * @param userId
	 * @return
	 */
	public UserModel findUserById(final String userId);

	/**
	 * @param userId
	 * @param userName
	 * @param page
	 * @param size
	 * @return
	 */
	public PaginationSupport findUser(final String userId, final String userName, final Integer startIndex, final Integer pageSize);
}
