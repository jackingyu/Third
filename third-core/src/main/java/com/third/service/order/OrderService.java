package com.third.service.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;

public interface OrderService {
	OrderModel getOrderForCode(final String orderCode);

	void createOrder(final OrderModel order);

	void upateOrder(final OrderModel order);

	PaginationSupport getOrders(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);
	
	PaginationSupport getOrdersByOrderDate(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String[]> sp);

	PaginationSupport getOrderEntries(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);
	
	List<OrderModel> getOrders(final Map<String, String> sp);

	void createOrderEntry(final OrderEntryModel orderEntry);

	void updateOrderEntry(final OrderEntryModel orderEntry);

	void removeOrderEntry(final String orderEntryPK);

	OrderEntryModel getOrderEntry(final String entryPK);

	OrderEntryModel getOrderEntryForExternalId(final String externalId);

	List<OrderModel> getOrdersForCustomer(final String customerPK);
	
	List<Object[]> anlysisOrder(Date startDate, Date endDate,Map<String, String[]> sp);
}
