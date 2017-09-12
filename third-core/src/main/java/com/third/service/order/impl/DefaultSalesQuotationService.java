package com.third.service.order.impl;

import java.util.Date;
import java.util.Map;

import com.third.dao.order.SalesQuotationDao;
import com.third.dao.util.PaginationSupport;
import com.third.model.SalesQuotationModel;
import com.third.service.order.SalesQuotationService;

public class DefaultSalesQuotationService implements SalesQuotationService {
	private SalesQuotationDao salesQuotationDao;

	@Override
	public void saveSalesQuotation(SalesQuotationModel salesQuotation)
	{
		this.salesQuotationDao.save(salesQuotation);
	}

	@Override
	public PaginationSupport getSalesQuotations(Date startDate, Date endDate,
			Integer startIndex, Integer pageSize, Map<String, String> sp)
	{
		return salesQuotationDao.findSalesQuotations(startDate, endDate, startIndex,
				pageSize, sp);
	}

	@Override
	public SalesQuotationModel getSalesQuotation(String pk)
	{
		return this.salesQuotationDao.get(pk);
	}

	public void setSalesQuotationDao(SalesQuotationDao salesQuotationDao)
	{
		this.salesQuotationDao = salesQuotationDao;
	}

}
