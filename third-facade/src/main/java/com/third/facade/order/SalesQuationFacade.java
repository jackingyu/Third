package com.third.facade.order;

import java.util.Date;
import java.util.Map;

import com.third.facade.data.DTResults;
import com.third.facade.data.SalesQuationData;

public interface SalesQuationFacade {
	String saveSalesQuation(SalesQuationData salesQuationData);

	void convertQuation2Order(SalesQuationData salesQuationData);

	DTResults getSalesQuation(Date startDate, Date endDate, Integer startIndex,
			Integer pageSize, Map<String, String> sp);
	
	SalesQuationData getSalesQuation(final String pk);

}
