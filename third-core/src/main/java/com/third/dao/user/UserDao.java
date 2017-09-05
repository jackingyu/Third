package com.third.dao.user;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserModel;


public interface UserDao extends IGenericDAO<UserModel, String>
{

	/**
	 * @param userId
	 * @return
	 */
	UserModel findUserById(final String userId);

	/**
	 * @param userId
	 * @param userName
	 * @param page
	 * @param size
	 * @return
	 */
	PaginationSupport findUser(final String userId, final String userName, final Integer startIndex, final Integer pageSize);

   List<UserModel> findSalesPerson(final String storeCode);
}
