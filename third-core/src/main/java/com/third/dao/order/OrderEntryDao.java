package com.third.dao.order;

import java.util.Date;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;


public interface OrderEntryDao extends IGenericDAO<OrderEntryModel, String>
{
	Integer countOrderEntryByOrder(final String orderPK);
}
