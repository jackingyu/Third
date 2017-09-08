package com.third.facade.store;

import java.util.Date;
import java.util.Map;

import com.third.facade.data.DTResults;

public interface FIReportFacade {
	DTResults getPaymentList(Date startDate, Date endDate, Integer startIndex,
			Integer pageSize, Map<String, String[]> sp);

}
