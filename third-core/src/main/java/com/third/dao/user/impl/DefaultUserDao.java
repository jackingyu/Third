package com.third.dao.user.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.third.dao.generic.GenericDAO;
import com.third.dao.user.UserDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.UserGroupModel;
import com.third.model.UserModel;

public class DefaultUserDao extends GenericDAO<UserModel, String>
		implements UserDao {

	private final static String FIND_BY_ID_SQL = "from com.third.model.UserModel u where u.userId=?";

	public UserModel findUserById(final String userId)
	{
		List<UserModel> users = this.find(FIND_BY_ID_SQL, userId);
		return CollectionUtils.isEmpty(users) ? null : users.get(0);
	}

	@Override
	public PaginationSupport findUser(String userId, String userName,
			Integer startIndex, Integer pageSize)
	{
		DetachedCriteria dcUser = DetachedCriteria.forClass(UserModel.class);

		if (!StringUtils.isEmpty(userId))
			dcUser.add(
					Restrictions.like("userId", generateLikeParameter(userId)));

		if (!StringUtils.isEmpty(userName))
			dcUser.add(
					Restrictions.like("name", generateLikeParameter(userName)));

		PaginationSupport ps = findPageByCriteria(dcUser, pageSize, startIndex);

		return ps;
	}

	public List<UserModel> findSalesPerson(final String storeCode)
	{
		final StringBuilder sb = new StringBuilder(
				"from UserModel u where u.userGroup.groupId ='sales' ");

		if (StringUtils.isNotEmpty(storeCode))
			sb.append("and u.store.id='").append(storeCode).append("'");

		return find(sb.toString());
	}

}
