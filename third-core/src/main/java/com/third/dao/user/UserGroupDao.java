package com.third.dao.user;

import java.util.List;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserGroupModel;

public interface UserGroupDao extends IGenericDAO<UserGroupModel, String> {
	/**
	 * @param id
	 * @return
	 */
	public UserGroupModel findUserGroupById(final String id);

	/**
	 * @param userGroupId
	 * @param userGroupName
	 * @return
	 */
	public PaginationSupport findUserGroupByName(final String userGroupId,
			final String userGroupName, final Integer startIndex,
			final Integer pageSize);

}
