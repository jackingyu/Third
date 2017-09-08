package com.third.dao.customer.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.third.dao.customer.SourceDao;
import com.third.dao.generic.GenericDAO;
import com.third.model.CustomerModel;
import com.third.model.SourceModel;

public class DefaultSourceDao extends GenericDAO<SourceModel, String>
		implements SourceDao {

	@Override
	public List<SourceModel> findSourceByName(String name)
	{
		DetachedCriteria dcSource = DetachedCriteria
				.forClass(SourceModel.class);

		if (!StringUtils.isEmpty(name))
			dcSource.add(Restrictions.like("name", name));

		return findByCriteria(dcSource);
	}

	@Override
	public boolean checkIfExist(String name)
	{
		DetachedCriteria dcSource = DetachedCriteria
				.forClass(SourceModel.class);

		if (!StringUtils.isEmpty(name))
			dcSource.add(Restrictions.eq("name", name));

		dcSource.addOrder(Order.desc("modificationTime"));

		List<SourceModel> sources = findByCriteria(dcSource);

		return CollectionUtils.isNotEmpty(sources);
	}

}
