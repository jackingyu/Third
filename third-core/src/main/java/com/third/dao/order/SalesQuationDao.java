package com.third.dao.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.third.dao.generic.IGenericDAO;
import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuationModel;


public interface SalesQuationDao extends IGenericDAO<SalesQuationModel, String>
{
	PaginationSupport findSalesQuations(final Date startDate, final Date endDate, final Integer startIndex, final Integer pageSize,
			final Map<String, String> sp);
	
}
