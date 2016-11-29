package com.third.facade.order;

import java.util.Date;
import java.util.Map;

import com.third.facade.data.ListData;
import com.third.facade.data.OrderData;
import com.third.facade.data.OrderEntryData;


public interface OrderFacade
{
	void createOrder(OrderData orderData);

	ListData getOrders(Date startDate, Date endDate, Integer startIndex, Integer pageSize, Map<String, String> sp);

	OrderData getOrder(final String orderCode);

	void updateOrder(final OrderData order);

	void createOrderEntry(final OrderEntryData orderEntryData);

	void updateOrderEntry(final OrderEntryData orderEntryData);

	void removeOrderEntry(final String orderEntryPK);

}
