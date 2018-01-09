package com.third.dao.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderProcessRecordModel;

public interface OrderProcessRecordDao
		extends IGenericDAO<OrderProcessRecordModel, String> {

	PaginationSupport findOrderProcessRecord(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize,
			Map<String, String[]> searchParameter);

	List<OrderProcessRecordModel> findOrderProcessForOrder(
			final String orderCode);

}
