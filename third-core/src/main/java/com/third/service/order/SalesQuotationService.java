package com.third.service.order;

import java.util.Date;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuotationModel;

public interface SalesQuotationService {
	void saveSalesQuotation(final SalesQuotationModel salesQuotation);

	PaginationSupport getSalesQuotations(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);

	SalesQuotationModel getSalesQuotation(final String pk);

}
