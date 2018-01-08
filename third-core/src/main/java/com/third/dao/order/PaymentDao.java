package com.third.dao.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.PaymentModel;

public interface PaymentDao extends IGenericDAO<PaymentModel, String> {
	PaginationSupport findPayments(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String[]> sp);
	
	BigDecimal sumPayments(final Date startDate, final Date endDate,final Map<String, String[]> sp,final String paymentMethod);
	
	BigDecimal[] sumPaymentsByOrder(final Date startDate, final Date endDate,final Map<String, String[]> sp);

	PaginationSupport findPaymentsByOrderDate(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp);

	List<Object[]> getTotalPaymentsByMethod(Date startDate, Date endDate,
			Map<String, String[]> sp);
}
