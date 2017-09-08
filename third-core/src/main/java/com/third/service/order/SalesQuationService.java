package com.third.service.order;

import java.util.Date;
import java.util.Map;

import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuationModel;

public interface SalesQuationService {
	void saveSalesQuation(final SalesQuationModel salesQuation);

	PaginationSupport getSalesQuations(final Date startDate, final Date endDate,
			final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);

	SalesQuationModel getSalesQuation(final String pk);

}
