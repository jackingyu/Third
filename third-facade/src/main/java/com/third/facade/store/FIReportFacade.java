package com.third.facade.store;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.facade.data.DTResults;

public interface FIReportFacade {
	DTResults getPaymentList(Date startDate, Date endDate, Integer startIndex,
			Integer pageSize, Map<String, String[]> sp);
	
	/**
	 * @param startDate
	 * @param endDate
	 * @param sp
	 * @return cash,creditcard,alipay,receivable,open,paid
	 */
	BigDecimal[] getPaymentListSummary(Date startDate, Date endDate, Map<String, String[]> sp);


	DTResults getStoreDashboardResult1(Date startDate, Date endDate,
			Map<String, String[]> sp);


	void storeHealthCheck(Date startDate, Date endDate,
			Map<String, String[]> sp);

	DTResults getDashboardPaymentDetails(Date startDate, Date endDate,
			Map<String, String[]> sp, int startIndex, int pageSize);


	List<Object[]> getDashboardPaymentsByMethod(Date startDate, Date endDate,
			Map<String, String[]> sp);

}
