package com.third.facade.order;

import java.util.Date;
import java.util.Map;

import com.third.facade.data.DTResults;
import com.third.facade.data.SalesQuotationData;

public interface SalesQuotationFacade {
	String saveSalesQuotation(SalesQuotationData salesQuotationData);

	void convertQuotation2Order(SalesQuotationData salesQuotationData);

	DTResults getSalesQuotation(Date startDate, Date endDate, Integer startIndex,
			Integer pageSize, Map<String, String> sp);
	
	SalesQuotationData getSalesQuotation(final String pk);
	
	String convert2SalesOrder(final String pk);

}
