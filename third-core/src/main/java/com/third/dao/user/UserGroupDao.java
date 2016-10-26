package com.third.dao.user;

import com.third.dao.generic.IGenericDAO;
import com.third.model.UserGroupModel;

public interface UserGroupDao extends IGenericDAO<UserGroupModel, String> {
	public UserGroupModel findUserGroupById(final String id);
}
