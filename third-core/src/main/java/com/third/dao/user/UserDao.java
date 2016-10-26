package com.third.dao.user;

import com.third.dao.generic.IGenericDAO;
import com.third.model.UserModel;

public interface UserDao extends IGenericDAO<UserModel, String> {

	public UserModel findUserById(final String userId);
}
