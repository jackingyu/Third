package com.third.dao.user.impl;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.UserGroupDao;
import com.third.model.UserGroupModel;


public class DefaultUserGroupDao extends GenericDAO<UserGroupModel, String> implements UserGroupDao
{

	private final static String FIND_BY_ID_SQL = "from com.third.model.UserGroupModel u where u.groupId=?";

	public UserGroupModel findUserGroupById(final String userGroupId)
	{
		List<UserGroupModel> userGroups = find(FIND_BY_ID_SQL, new String[]
		{ userGroupId });

		return CollectionUtils.isEmpty(userGroups) ? null : userGroups.get(0);
	}

}
