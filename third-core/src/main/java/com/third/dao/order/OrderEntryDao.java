package com.third.dao.order;

import com.third.dao.generic.IGenericDAO;
import com.third.model.OrderEntryModel;


public interface OrderEntryDao extends IGenericDAO<OrderEntryModel, String>
{
	Integer countOrderEntryByOrder(final String orderPK);

	OrderEntryModel findOrderEntryByExternalId(final String externalId);
}
