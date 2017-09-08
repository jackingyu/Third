package com.third.dao.user.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.UserGroupDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserGroupModel;

public class DefaultUserGroupDao extends GenericDAO<UserGroupModel, String>
		implements UserGroupDao {

	private final static String FIND_BY_ID_SQL = "from com.third.model.UserGroupModel u where u.groupId=?";
	private final static String FIND_BY_IDANDNAME_SQL = "from com.third.model.UserGroupModel u where u.groupId like ? and u.name like ?";

	public UserGroupModel findUserGroupById(final String userGroupId)
	{
		List<UserGroupModel> userGroups = find(FIND_BY_ID_SQL,
				new String[] { userGroupId });

		return CollectionUtils.isEmpty(userGroups) ? null : userGroups.get(0);
	}

	@Override
	public PaginationSupport findUserGroupByName(final String userGroupId,
			final String userGroupName, final Integer startIndex,
			final Integer pageSize)
	{
		DetachedCriteria dcUserGroup = DetachedCriteria
				.forClass(UserGroupModel.class);

		if (!StringUtils.isEmpty(userGroupId))
			dcUserGroup.add(Restrictions.like("groupId",
					generateLikeParameter(userGroupId)));

		if (!StringUtils.isEmpty(userGroupName))
			dcUserGroup.add(Restrictions.like("name",
					generateLikeParameter(userGroupName)));

		PaginationSupport ps = findPageByCriteria(dcUserGroup, pageSize,
				startIndex);
		return ps;
	}
}
