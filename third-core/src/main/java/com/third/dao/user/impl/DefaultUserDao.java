package com.third.dao.user.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.UserDao;
import com.third.model.UserModel;


public class DefaultUserDao extends GenericDAO<UserModel, String> implements UserDao
{

	private final static String FIND_BY_ID_SQL = "from com.third.model.UserModel u where u.userId=?";

	public UserModel findUserById(final String userId)
	{
		List<UserModel> users = this.find(FIND_BY_ID_SQL, userId);
		return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}
}
