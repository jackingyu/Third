package com.third.dao.order.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderEntryDao;
import com.third.model.OrderEntryModel;


public class DefaultOrderEntryDao extends GenericDAO<OrderEntryModel, String> implements OrderEntryDao
{
	private final String COUNT_ENTRY_BY_ORDER = "select count(*) from com.third.model.OrderEntryModel e where e.order.pk=:orderPK";

	@Override
	public Integer countOrderEntryByOrder(final String orderPK)
	{
		Long numberOfEntry = (Long) this.currentSession().createQuery(COUNT_ENTRY_BY_ORDER).setParameter("orderPK", orderPK)
				.uniqueResult();
		return numberOfEntry.intValue();
	}
	
	@Override
	public OrderEntryModel findOrderEntryByExternalId(final String externalId)
	{
		DetachedCriteria dcOrderEntry = DetachedCriteria.forClass(OrderEntryModel.class);
		dcOrderEntry.add(Restrictions.eq("externalId", externalId));
		List<OrderEntryModel> entry = findByCriteria(dcOrderEntry);
		
		return CollectionUtils.isNotEmpty(entry)?entry.get(0):null;
	}
}
