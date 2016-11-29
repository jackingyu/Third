package com.third.dao.order.impl;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderEntryDao;
import com.third.model.OrderEntryModel;


public class DefaultOrderEntryDao extends GenericDAO<OrderEntryModel, String> implements OrderEntryDao
{
	private final String COUNT_ENTRY_BY_ORDER = "select count(*) from com.third.model.OrderEntryModel e where e.order.pk=:orderPK";

	@Override
	public Integer countOrderEntryByOrder(String orderPK)
	{
		Long numberOfEntry = (Long) this.currentSession().createQuery(COUNT_ENTRY_BY_ORDER).setParameter("orderPK", orderPK)
				.uniqueResult();
		return numberOfEntry.intValue();
	}
}
