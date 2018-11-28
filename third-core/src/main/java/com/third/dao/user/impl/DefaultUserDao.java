package com.third.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.UserDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultUserDao extends GenericDAO<UserModel, String>
		implements UserDao {

	private final static String FIND_BY_ID_SQL = " from com.third.model.UserModel u where u.userId=?0";

	public UserModel findUserById(final String userId)
	{
		List<UserModel> users = this.find(FIND_BY_ID_SQL, userId);
		return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}

	@Override
	public PaginationSupport findUser(String userId, String userName,String storeCode,
			Integer startIndex, Integer pageSize)
	{
		final StringBuilder sb = new StringBuilder(
				"from com.third.model.UserModel u ");
		List<String> condition = new ArrayList<String>();

		if (StringUtils.isNotEmpty(userId))
			condition.add("u.userId like '"+generateLikeParameter(userId)+"'");

		if (StringUtils.isNotEmpty(userName))
			condition.add("u.name like '"+generateLikeParameter(userName)+"'");

		
		if (StringUtils.isNotEmpty(storeCode))
			condition.add("u.store.id='"+storeCode+"'");

		if(CollectionUtils.isNotEmpty(condition))
		{
			sb.append("where ").append(StringUtils.join(condition.toArray(), " and "));
		}
		
		PaginationSupport ps = findPageByQuery(sb.toString(), pageSize, startIndex);

		return ps;
	}

	@Override
	public List<UserModel> findUserByUserGroup(final String storeCode,final String userGroupId)
	{
		final StringBuilder sb = new StringBuilder(
				"from UserModel u where u.userGroup.groupId ='").append(userGroupId).append("' ");

		if (StringUtils.isNotEmpty(storeCode))
			sb.append("and u.store.id='").append(storeCode).append("'");

		return find(sb.toString());
	}

}
