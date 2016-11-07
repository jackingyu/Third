package com.third.dao.customer.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.third.dao.customer.CustomerDao;
import com.third.dao.generic.GenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.CustomerModel;


public class DefaultCustomerDao extends GenericDAO<CustomerModel, String> implements CustomerDao
{
	private final static String FIND_BY_CELLPHONE_SQL = "from com.third.model.CustomerModel c where c.cellphone=?";

	@Override
	public CustomerModel getCustomerByCellphone(String cellphone)
	{
		List<CustomerModel> customers = this.find(FIND_BY_CELLPHONE_SQL, cellphone);
		return CollectionUtils.isEmpty(customers) ? null : customers.get(0);
	}

	@Override
	public PaginationSupport findCustomer(final String cellphone, final String name, Integer startIndex, Integer pageSize)
	{
		DetachedCriteria dcCustomer = DetachedCriteria.forClass(CustomerModel.class);

		if (!StringUtils.isEmpty(cellphone))
			dcCustomer.add(Restrictions.like("cellphone", generateLikeParameter(cellphone)));

		if (!StringUtils.isEmpty(name))
			dcCustomer.add(Restrictions.like("name", generateLikeParameter(name)));

		PaginationSupport ps = findPageByCriteria(dcCustomer, pageSize, startIndex);

		return ps;
	}



}
