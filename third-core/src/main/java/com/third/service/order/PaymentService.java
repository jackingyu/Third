package com.third.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.model.OrderModel;
import com.third.model.PaymentModel;

public interface PaymentService {
	void createPayment(PaymentModel payment);

	void removePayment(final String paymentPK);

	PaymentModel getPayment(final String paymentPK);

	PaginationSupport getPayments(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String[]> sp);
	
	BigDecimal sumPaymentsByPaymentMethod(final Date startDate, final Date endDate,final Map<String, String[]> sp,final
	        String paymentMethod);
	

	PaginationSupport getPaymentsByOrderDate(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String[]> sp);


	List<Object[]> getTotalPaymentsByMethod(Date startDate, Date endDate,Map<String, String[]> sp);

    BigDecimal[] sumPaymentsByOrder(Date startDate, Date endDate, Map<String, String[]> sp);
}
