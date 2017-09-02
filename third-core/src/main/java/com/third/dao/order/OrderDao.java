package com.third.dao.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;


public interface OrderDao extends IGenericDAO<OrderModel, String>
{
	OrderModel findOrder(final String orderNo);

	PaginationSupport findOrders(Date startDate, Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String> searchParameter);

	List<OrderModel> findOrdersForCustomer(final String customerPK);
	
	Integer countOrderForCustomer(final String cellphone);
	
}
