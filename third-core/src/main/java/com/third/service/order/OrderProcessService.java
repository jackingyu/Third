package com.third.service.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.exceptions.NoQualifiedTargetStatusException;
import com.third.model.OrderEntryModel;
import com.third.model.OrderModel;
import com.third.model.OrderProcessRecordModel;
import com.third.model.UserModel;


public interface OrderProcessService
{
	void createOrderProcess(final OrderProcessRecordModel orderProcessRecord);
	
	void updateOrderProcess(final OrderProcessRecordModel orderProcessRecord);
	
	PaginationSupport getOrderProcessRecords(final Date startDate, final Date endDate, final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);
	
	List<OrderProcessRecordModel> getOrderProcessRecordForOrder(final String orderCode);

	OrderProcessRecordModel processOrder(OrderModel order, UserModel user,Integer targetStatus) throws NoQualifiedTargetStatusException;
	
	OrderProcessRecordModel processOrderEntry(OrderEntryModel orderEntry, UserModel user,Integer targetStatus) throws NoQualifiedTargetStatusException;

}
