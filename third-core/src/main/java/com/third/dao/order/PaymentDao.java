package com.third.dao.order;

import java.util.Date;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.PaymentModel;


public interface PaymentDao extends IGenericDAO<PaymentModel, String>
{
	PaginationSupport findPayments(final Date startDate, final Date endDate, final Integer startIndex, final Integer pageSize,
			final Map<String, String[]> sp);
}
