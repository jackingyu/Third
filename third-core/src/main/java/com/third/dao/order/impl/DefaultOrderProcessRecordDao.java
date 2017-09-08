package com.third.dao.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.generic.GenericDAO;
import com.third.dao.order.OrderProcessRecordDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.OrderProcessRecordModel;

public class DefaultOrderProcessRecordDao
		extends GenericDAO<OrderProcessRecordModel, String>
		implements OrderProcessRecordDao {
	private final static String FIND_BY_ORDERCODE_SQL = "from com.third.model.OrderProcessRecordModel op where op.orderCode=?";

	@Override
	public PaginationSupport findOrderProcessRecord(Date startDate,
			Date endDate, Integer startIndex, Integer pageSize,
			Map<String, String> searchParameter)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderProcessRecordModel> findOrderProcessForOrder(
			String orderCode)
	{
		List<OrderProcessRecordModel> or = find(FIND_BY_ORDERCODE_SQL,
				orderCode);
		return or;
	}

}
